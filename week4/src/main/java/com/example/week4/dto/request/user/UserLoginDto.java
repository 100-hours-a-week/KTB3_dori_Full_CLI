package com.example.week4.dto.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDto {

    private String email;
    private String password;
}
