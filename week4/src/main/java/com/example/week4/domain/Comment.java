package com.example.week4.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Comment {

    private String content;
    //created Date

    @Builder
    public Comment(String content) {
        this.content = content;
    }
}
