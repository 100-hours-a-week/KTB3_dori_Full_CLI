package com.example.week4.controller;

import com.example.week4.dto.response.post.PostDetailResponse;
import com.example.week4.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailResponse> getPost(@PathVariable Long id) {
        PostDetailResponse post = postService.getPost(id);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
