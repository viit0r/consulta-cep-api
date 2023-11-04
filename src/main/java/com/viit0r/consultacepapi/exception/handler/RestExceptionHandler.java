package com.viit0r.consultacepapi.exception.handler;

import com.viit0r.consultacepapi.exception.CEPNotFoundException;
import com.viit0r.consultacepapi.exception.ExceptionResponse;
import com.viit0r.consultacepapi.exception.InvalidCEPException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidCEPException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidCEPException(InvalidCEPException exception) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(exception.getMessage(), new Date());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CEPNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleCEPNotFoundException(CEPNotFoundException exception) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(exception.getMessage(), new Date());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
