package com.example.week5.dto.response.user;

import com.example.week5.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private String email;

    @Builder
    public LoginResponse(String token, String email) {
        this.token = token;
        this.email = email;
    }

    public static LoginResponse fromEntity(User user, String token) {
        return LoginResponse
                .builder()
                .token(token)
                .email(user.getEmail())
                .build();
    }
}
