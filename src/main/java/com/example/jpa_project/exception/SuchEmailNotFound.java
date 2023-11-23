package com.example.jpa_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SuchEmailNotFound extends RuntimeException {

    private String message;

    public SuchEmailNotFound(String message) {
        super(message);
    }
}
