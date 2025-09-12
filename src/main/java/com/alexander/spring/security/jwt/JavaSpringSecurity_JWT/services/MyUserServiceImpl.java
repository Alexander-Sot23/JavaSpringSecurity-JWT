package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.services;

import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.dtos.MyUserDTO;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.MyUser;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.exceptions.*;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.models.PasswordUpdateRequestM;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MyUserServiceImpl implements MyUserService{

    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Set<MyUserDTO> findAll(Pageable pageable) {
        return myUserRepository.findAll(pageable).stream()
                .map(MyUserDTO::new)
                .collect(Collectors.toSet());
    }

    @Override
    public MyUserDTO findByUserName(String username) {
        return new MyUserDTO(myUserRepository.findByUserName(username)
                .orElseThrow(() -> new UserNameNotFoundException(username)));
    }

    @Override
    public MyUserDTO findByEmail(String email) {
        return new MyUserDTO(myUserRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(email)));
    }

    @Override
    public MyUserDTO save(MyUser myUser) {
        checkAttributes(myUser);
        return new MyUserDTO(myUserRepository.save(myUser));
    }

    @Override
    public Set<MyUserDTO> saveAll(List<MyUser> myUsers) {
        for(MyUser myUser: myUsers){
            checkAttributes(myUser);
        }
        return myUsers.stream()
                .map(this::save)
                .collect(Collectors.toSet());
    }

    @Override
    public MyUserDTO update(String username, MyUser myUser) {
        MyUser myUserDB = myUserRepository.findByUserName(username)
                .orElseThrow(() -> new UserNameNotFoundException(username));
        myUserDB.setName(myUser.getName());
        myUserDB.setLastName(myUser.getLastName());
        myUserDB.setGender(myUser.getGender());
        myUserDB.setBirthDate(myUser.getBirthDate());
        return new MyUserDTO(myUserRepository.save(myUserDB));
    }

    @Override
    public MyUserDTO updatePassword(String username,PasswordUpdateRequestM password) {
        MyUser myUser = myUserRepository.findByUserName(username)
                .orElseThrow(() -> new UserNameNotFoundException(username));
        if (!passwordEncoder.matches(password.oldPassword(), myUser.getPassword())) {
            throw new PasswordDoesNotMatchException();
        }
        myUser.setPassword(passwordEncoder.encode(password.newPassword()));
        return new MyUserDTO(myUserRepository.save(myUser));
    }

    @Override
    public void delete(String username) {
        myUserRepository.delete(myUserRepository.findByUserName(username)
                .orElseThrow(() -> new UserNameNotFoundException(username)));
    }

    private void checkAttributes(MyUser myUser){
        if(myUserRepository.existsByUserName(myUser.getUserName())){
            throw new UserNameExistsException(myUser.getUserName());
        }
        if(myUserRepository.existsByEmail(myUser.getEmail())){
            throw new EmailExistsException(myUser.getEmail());
        }
    }
}
