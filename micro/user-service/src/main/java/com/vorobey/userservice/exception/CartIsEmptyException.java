package com.vorobey.userservice.exception;

public class CartIsEmptyException extends RuntimeException {

    public static final String EXCEPTION_MESSAGE = "Cart is empty : nothing to order";

    public CartIsEmptyException() {
        super(EXCEPTION_MESSAGE);
    }

    public CartIsEmptyException(String message) {
        super(message);
    }
}
