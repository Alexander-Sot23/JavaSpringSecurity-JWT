package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.exceptions;

public class UserNameExistsException extends RuntimeException{
    public UserNameExistsException(String username){
        super("El nombre de usuario: " + username + ", ya esta registrado.");
    }
}
