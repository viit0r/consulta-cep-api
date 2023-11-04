package com.viit0r.consultacepapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCEPException extends RuntimeException {
    public InvalidCEPException(String ex) {
        super(ex);
    }
}
