package com.nvs.store.exceptions;

public class UserNotFoundException extends IllegalArgumentException{
    private final String userName;

    public UserNotFoundException(String userName) {
        super();
        this.userName = userName;
    }

    public String getUserName() {
        return userName;

    }
}
