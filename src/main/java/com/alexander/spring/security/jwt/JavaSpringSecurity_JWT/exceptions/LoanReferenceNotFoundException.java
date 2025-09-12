package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.exceptions;

public class LoanReferenceNotFoundException extends RuntimeException{
    public LoanReferenceNotFoundException(String loanReference){
        super("El prestamo con la referencia: " + loanReference + ", no fue encontrado.");
    }
}
