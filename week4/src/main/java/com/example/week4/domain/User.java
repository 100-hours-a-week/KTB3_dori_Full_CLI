package com.example.week4.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class User {

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String createdDate;
    private String profile_image;


    @Builder
    public User(String email, String password, String nickname, String profile_image) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profile_image = profile_image;
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

    public void generateId(Long id) {
        this.id = id;
    }
}
