package org.example.whatsappbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ApplicationException.class)
    public ExceptionResponse handleException(ApplicationException e) {
        return new ExceptionResponse(e.getCode(), e.getMessage(), LocalDateTime.now());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ExceptionResponse handleException(Exception e) {
        return new ExceptionResponse("UNKNOWN_EXCEPTION", e.getMessage(), LocalDateTime.now());
    }
}
