package com.example.week4.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String profile_image;


    @Builder
    public User(String email, String password, String nickname, String profile_image) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profile_image = profile_image;
    }
}
