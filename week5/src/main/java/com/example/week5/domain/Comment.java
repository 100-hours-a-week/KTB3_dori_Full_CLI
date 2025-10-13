package com.example.week5.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class Comment {

    private Long id;
    private String content;
    private User user;
    private Post post;

    private String createdDate;
    private String modifiedDate;


    @Builder
    public Comment(String content, User user, Post post) {
        this.content = content;
        this.user = user;
        this.post = post;
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        this.modifiedDate = createdDate;
    }

    public void generateId(Long id) {
        this.id = id;
    }

    public void update(String content) {
        this.content = content;
        this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

    public void setMappingPost(Post post) {
        this.post = post;
    }

    public void setMappingUser(User user) {
        this.user = user;
    }
}
