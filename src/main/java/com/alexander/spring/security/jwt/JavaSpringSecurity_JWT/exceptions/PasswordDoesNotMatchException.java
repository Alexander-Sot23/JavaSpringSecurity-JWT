package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.exceptions;

public class PasswordDoesNotMatchException extends RuntimeException{
    public PasswordDoesNotMatchException(){
        super("La contraseña antigua ingresada no coindice con la contraseña actual del usuario.");
    }
}
