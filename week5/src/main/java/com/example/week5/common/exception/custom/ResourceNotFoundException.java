package com.example.week5.common.exception.custom;

import com.example.week5.common.exception.ErrorMessage;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
