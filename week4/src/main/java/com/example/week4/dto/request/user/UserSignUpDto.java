package com.example.week4.dto.request.user;

import com.example.week4.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSignUpDto {

    private String email;
    private String password;
    private String passwordCheck;
    private String nickname;

    @Builder
    public UserSignUpDto(String email, String password, String passwordCheck, String nickname) {
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.nickname = nickname;
    }

    @Builder
    public static User toEntity(UserSignUpDto dto) {
        return User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .nickname(dto.getNickname())
                .build();
    }
}
