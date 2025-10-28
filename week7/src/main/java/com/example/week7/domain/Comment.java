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
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
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
        post.getComments().add(this);
    }

    public void setMappingUser(User user) {
        this.user = user;
    }
}
