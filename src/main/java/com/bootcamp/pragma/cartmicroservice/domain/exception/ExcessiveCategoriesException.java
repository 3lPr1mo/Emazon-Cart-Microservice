package com.bootcamp.pragma.cartmicroservice.domain.exception;

public class ExcessiveCategoriesException extends RuntimeException {
    public ExcessiveCategoriesException(String message) {
        super(message);
    }
}
