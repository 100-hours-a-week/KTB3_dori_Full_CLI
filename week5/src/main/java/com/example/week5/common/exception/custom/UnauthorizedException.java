package com.example.week5.common.exception.custom;

import com.example.week5.common.exception.ErrorMessage;

public class UnauthorizedException extends CustomException{

    public UnauthorizedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
