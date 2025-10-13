package com.example.week5.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class Post {

    private Long id;
    private String title;
    private String content;
    private Long viewCount;
    private List<Comment> comments = new ArrayList<>();
    private User user;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.viewCount = 0L;
        this.user = user;
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        this.modifiedDate = createdDate;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

    public void generateId(Long id) {
        this.id = id;
    }

    // 사용자 등록
    public void setMappingUser(User user) {
        this.user = user;
    }

    public void upViewcount() {
        this.viewCount++;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setMappingPost(this);
    }

    public void deleteComment(Comment comment) {
        comments.remove(comment);
        comment.setMappingPost(null);
    }
}
