package com.example.week4.dto.response.user;

import com.example.week4.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
