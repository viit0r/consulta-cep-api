package com.viit0r.consultacepapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CEPNotFoundException extends RuntimeException {
    public CEPNotFoundException(String ex) {
        super(ex);
    }
}
