package com.movie_rental_system.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MovieReviewNotFoundException extends RuntimeException {
    public MovieReviewNotFoundException(String message) {
        super(message);
    }
}
