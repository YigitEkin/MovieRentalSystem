package com.movie_rental_system.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.CONFLICT)
public class FavoriteAlreadyExistsException extends RuntimeException {
    public FavoriteAlreadyExistsException(String message) {
        super(message);
    }
}
