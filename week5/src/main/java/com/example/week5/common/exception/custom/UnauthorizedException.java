package com.example.week5.common.exception.custom;

import com.example.week5.common.exception.ErrorMessage;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
