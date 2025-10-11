package com.example.week4.common.exception.custom;

public class DuplicatedException extends RuntimeException {
    public DuplicatedException(String message) {
        super(message);
    }
}