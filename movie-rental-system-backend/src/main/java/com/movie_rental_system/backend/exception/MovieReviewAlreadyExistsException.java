package com.movie_rental_system.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class MovieReviewAlreadyExistsException extends RuntimeException {
    public MovieReviewAlreadyExistsException(String message) {
        super(message);
    }
}
