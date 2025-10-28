package com.example.week7.controller;

import com.example.week7.common.response.APIResponse;
import com.example.week7.service.like.LikeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/likes")
public class LikeController {

    private final LikeService likeService;

    @GetMapping
    public ResponseEntity<APIResponse<Long>> getPostLikes(@PathVariable Long postId) {
        long likeCount = likeService.getLikeCount(postId);
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.success("좋아요 조회 성공", likeCount));
    }

    @PostMapping
    public ResponseEntity<APIResponse<Long>> likePost(@PathVariable Long postId, HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        long likeCount = likeService.likePost(postId, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.success("좋아요 생성 성공", likeCount));
    }

    @DeleteMapping
    ResponseEntity<APIResponse<Long>> unlikePost(@PathVariable Long postId, HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        long likeCount = likeService.unlikePost(postId, email);
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.success("좋아요 삭제 성공", likeCount));
    }


}
