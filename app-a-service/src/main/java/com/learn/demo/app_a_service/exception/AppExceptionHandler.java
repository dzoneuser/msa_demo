package com.learn.demo.app_a_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex){

        ErrorMessage msg = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getMessage()
        );

        return new ResponseEntity<ErrorMessage>(msg, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorMessage> defaultException(Exception ex){

        ErrorMessage msg = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getMessage()
        );

        return new ResponseEntity<ErrorMessage>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
