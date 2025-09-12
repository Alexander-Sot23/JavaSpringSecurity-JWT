package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.services;

import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.dtos.MyUserDTO;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.MyUser;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.models.PasswordUpdateRequestM;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface MyUserService {
    Set<MyUserDTO> findAll(Pageable pageable);
    MyUserDTO findByUserName(String username);
    MyUserDTO findByEmail(String email);
    MyUserDTO save(MyUser myUser);
    Set<MyUserDTO> saveAll(List<MyUser> myUsers);
    MyUserDTO update(String username, MyUser myUser);
    MyUserDTO updatePassword(String username,PasswordUpdateRequestM password);
    void delete(String username);
}
