package com.vorobey.userservice.exception;

public class CartNotFoundException extends RuntimeException {

    public static final String EXCEPTION_MESSAGE = "Cart not found, please make a cart via adding some goods";

    public CartNotFoundException() {
        super(EXCEPTION_MESSAGE);
    }

    public CartNotFoundException(String message) {
        super(message);
    }
}
