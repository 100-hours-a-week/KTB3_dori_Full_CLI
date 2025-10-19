package com.example.week5.common.exception.custom;

import com.example.week5.common.exception.ErrorMessage;

public class DuplicatedException extends RuntimeException {

    public DuplicatedException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

    public DuplicatedException(String message) {
        super(message);
    }
}