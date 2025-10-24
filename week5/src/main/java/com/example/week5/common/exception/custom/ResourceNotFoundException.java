package com.example.week5.common.exception.custom;

import com.example.week5.common.exception.ErrorMessage;

public class ResourceNotFoundException extends CustomException {

    public ResourceNotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
