package com.microservices.userservice.shared.domain.exception;

public class NotFoundException extends RuntimeException {
    protected NotFoundException(String message){
        super(message);
    }
}
