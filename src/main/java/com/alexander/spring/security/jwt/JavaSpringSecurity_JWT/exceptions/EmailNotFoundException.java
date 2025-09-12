package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.exceptions;

public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException(String email){
        super("El correo electronico: " + email + ", no fue encontrado.");
    }
}
