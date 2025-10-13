package com.example.week5.dto.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangePasswordDto {

    private String password;
    private String passwordCheck;

    public ChangePasswordDto(String password, String passwordCheck) {
        this.password = password;
        this.passwordCheck = passwordCheck;
    }
}
