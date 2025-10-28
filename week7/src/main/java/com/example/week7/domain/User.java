package com.example.week7.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    private String email;

    private String password;

    private String nickname;

    private String createdDate;

    private String profileImage;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Builder
    public User(String email, String password, String nickname, String profileImage) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = Role.USER;
        this.profileImage = profileImage;
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

    public void update(String nickname, String profileImage) {
        this.nickname = nickname;
        this.profileImage = profileImage;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void generateId(Long id) {
        this.id = id;
    }
}
