package com.example.week4.common.exception.custom;

public class UserDuplicatedException extends RuntimeException {
    public UserDuplicatedException(String message) {
        super(message);
    }
}