package com.home.poll.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongRequestParametersException extends RuntimeException {
    public WrongRequestParametersException(String message) {
        super(message);
    }
}
