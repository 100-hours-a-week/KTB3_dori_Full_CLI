package com.example.week4.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Likes {

    private Long id;
    private Long userId;
    private Long postId;
    private boolean isLiked;

    public Likes(Long id, Long userId, Long postId) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.isLiked = false;
    }
}
