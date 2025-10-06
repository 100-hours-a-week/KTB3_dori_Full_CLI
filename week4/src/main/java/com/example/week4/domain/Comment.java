package com.example.week4.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Comment {

    private Long id;
    private String content;
    private User user;
    //created Date

    @Builder
    public Comment(String content) {
        this.content = content;
    }

    public void generateId(Long id) {
        this.id = id;
    }
}
