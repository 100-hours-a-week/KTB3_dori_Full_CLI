package com.example.week4.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Builder
    public Post(String title, String content, Long viewCount, List<Comment> comments, User user) {
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.comments = comments;
        this.user = user;
    }

    public void generateId(Long id) {
        this.id = id;
    }
}
