package com.foodie.odo.appservice.exception;

import com.foodie.common.exception.DomainException;

public class CustomerNotFoundException extends DomainException {
    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
