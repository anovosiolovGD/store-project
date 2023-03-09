package com.nvs.store.exceptions;

public class InvalidCartItemException extends IllegalArgumentException {
    public InvalidCartItemException(String msg) {
        super(msg);
    }
}
