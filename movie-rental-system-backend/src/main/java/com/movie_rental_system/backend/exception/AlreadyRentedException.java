package com.movie_rental_system.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AlreadyRentedException extends RuntimeException {
    public AlreadyRentedException(String message) {
        super(message);
    }
}
