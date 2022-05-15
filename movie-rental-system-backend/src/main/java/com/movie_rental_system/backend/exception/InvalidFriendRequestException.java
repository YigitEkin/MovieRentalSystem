package com.movie_rental_system.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class InvalidFriendRequestException extends RuntimeException{
    public InvalidFriendRequestException(String message) {
        super(message);
    }
}
