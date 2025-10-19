package com.example.week5.common.exception.custom;

import com.example.week5.common.exception.ErrorMessage;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
