package com.bootcamp.pragma.cartmicroservice.domain.exception;

public class UserHaveAlreadyAnActiveCartException extends RuntimeException {
    public UserHaveAlreadyAnActiveCartException(String message) {
        super(message);
    }
}
