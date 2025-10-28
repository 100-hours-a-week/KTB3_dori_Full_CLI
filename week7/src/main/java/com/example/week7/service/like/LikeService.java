package com.example.week7.service.like;

import org.springframework.stereotype.Service;

public interface LikeService {

    long likePost(Long postId, String email);
    long unlikePost(Long postId, String email);
    long getLikeCount(Long postId);
    void clearLikes(Long postId);

}
