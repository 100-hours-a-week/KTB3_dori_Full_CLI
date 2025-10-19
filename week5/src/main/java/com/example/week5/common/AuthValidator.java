package com.example.week5.common;

import com.example.week5.common.exception.ErrorMessage;
import com.example.week5.common.exception.custom.ForbiddenException;
import com.example.week5.domain.User;

public class AuthValidator {

    public static void validate(User loginUser, User owner) throws ForbiddenException {
        if (!loginUser.getId().equals(owner.getId())) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN);
        }
    }
}
