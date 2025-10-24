package com.example.week5.common.exception.custom;

import com.example.week5.common.exception.ErrorMessage;

public class BadRequestException extends CustomException {

    public BadRequestException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
