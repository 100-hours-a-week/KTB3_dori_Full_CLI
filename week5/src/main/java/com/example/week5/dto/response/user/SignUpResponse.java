package com.example.week5.dto.response.user;

import com.example.week5.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class SignUpResponse {

    private String email;

    @Builder
    public SignUpResponse(String email) {
        this.email = email;
    }

    public static SignUpResponse fromEntity(User user) {
        return SignUpResponse
                .builder()
                .email(user.getEmail())
                .build();
    }
}
