package com.jpa.practice.summary.controller.api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CustomExistException extends RuntimeException{
    public CustomExistException(String message) {
        super(message);
    }
}