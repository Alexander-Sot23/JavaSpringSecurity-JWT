package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.exceptions;

public class EmailExistsException extends RuntimeException{
    public EmailExistsException(String email){
        super("El email: " + email + ", ya esta registrado.");
    }
}
