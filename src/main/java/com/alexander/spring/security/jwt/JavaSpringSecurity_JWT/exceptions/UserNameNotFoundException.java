package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.exceptions;

public class UserNameNotFoundException extends RuntimeException{
    public UserNameNotFoundException(String username){
        super("El nombre de usuario: " + username + ", no fue encontrado en la base de datos.");
    }
}
