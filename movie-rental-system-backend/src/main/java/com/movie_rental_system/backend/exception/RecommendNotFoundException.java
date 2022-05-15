package com.movie_rental_system.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecommendNotFoundException extends RuntimeException {
    public RecommendNotFoundException(String message) {
        super(message);
    }
}
