package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.exceptions;

public class BookNotAvailableException extends RuntimeException{
    public BookNotAvailableException(String bookTitle){
        super("El libro: " + bookTitle + ", no esta disponible.");
    }
}
