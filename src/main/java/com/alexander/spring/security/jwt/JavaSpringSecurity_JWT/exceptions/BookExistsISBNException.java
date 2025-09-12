package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.exceptions;

public class BookExistsISBNException extends RuntimeException{
    public BookExistsISBNException(String isbn){
        super("El ISBN: " + isbn + ", ya esta registrado.");
    }
}
