package com.movie_rental_system.backend.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = org.springframework.http.HttpStatus.BAD_REQUEST)
public class InvalidDateFormatException extends RuntimeException{
    public InvalidDateFormatException(String message) {
        super(message);
    }
}
