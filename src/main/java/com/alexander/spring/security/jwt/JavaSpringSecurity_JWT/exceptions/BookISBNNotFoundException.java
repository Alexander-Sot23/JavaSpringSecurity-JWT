package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.exceptions;

public class BookISBNNotFoundException extends RuntimeException{
    public  BookISBNNotFoundException(String isbn){
        super("Libro con ISBN: " + isbn + ", no fue encontrado.");
    }
}
