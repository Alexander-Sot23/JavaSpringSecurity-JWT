package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.controllers;

import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.entities.MyUser;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.models.LoginFormM;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.services.MyUserDetailsService;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.services.MyUserService;
import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.webtoken.JWTService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private MyUserService myUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/register/user")
    public ResponseEntity<?> createUserBasic(@Valid @RequestBody MyUser myUser){
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        myUser.setRoles("USER");
        return ResponseEntity.status(HttpStatus.CREATED).body(myUserService.save(myUser));
    }

    @PostMapping("/admin/register/user")
    public ResponseEntity<?> createUserAdmin(@Valid @RequestBody MyUser myUser){
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(myUserService.save(myUser));
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@Valid @RequestBody LoginFormM loginFormM){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginFormM.username(),
                loginFormM.password()
        ));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(myUserDetailsService.loadUserByUsername(loginFormM.username()));
        }else{
            throw new UsernameNotFoundException("Credenciales invalidas.");
        }
    }

}
