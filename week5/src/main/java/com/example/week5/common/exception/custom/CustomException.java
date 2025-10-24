package com.example.week5.common.exception.custom;

import com.example.week5.common.exception.ErrorMessage;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public CustomException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}