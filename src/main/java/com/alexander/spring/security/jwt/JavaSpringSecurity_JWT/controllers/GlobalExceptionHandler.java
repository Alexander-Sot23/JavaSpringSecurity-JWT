package com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.controllers;

import com.alexander.spring.security.jwt.JavaSpringSecurity_JWT.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BookISBNNotFoundException.class, LoanReferenceNotFoundException.class,
            UserNameNotFoundException.class, EmailNotFoundException.class})
    public ResponseEntity<?> handlerNotFound(RuntimeException ex){
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler({EmailExistsException.class, BookExistsISBNException.class,
        UserNameExistsException.class})
    public ResponseEntity<?> handlerExists(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<>().put("message",ex.getMessage()));
    }

    @ExceptionHandler(BookNotAvailableException.class)
    public ResponseEntity<?> handlerAvailable(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<>().put("message",ex.getMessage()));
    }

    @ExceptionHandler(PasswordDoesNotMatchException.class)
    public ResponseEntity<?> handlerPasswordDoesNotMatch(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new HashMap<>().put("message",ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError)error).getField();
            errors.put(fieldName,error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
