package com.learn.demo.app_a_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private long id;

    public ResourceNotFoundException(long id, String msg) {
        super(msg);
        this.id = id;
    }
}
