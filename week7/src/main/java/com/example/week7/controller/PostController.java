package com.example.week7.controller;

import com.example.week7.common.response.APIResponse;
import com.example.week7.dto.request.post.PostRequestDto;
import com.example.week7.dto.response.post.PostCreateResponse;
import com.example.week7.dto.response.post.PostDetailResponse;
import com.example.week7.dto.response.post.PostListResponse;
import com.example.week7.service.like.LikeService;
import com.example.week7.service.post.PostServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostServiceImpl postService;
    private final LikeService likeService;

    @GetMapping
    public ResponseEntity<APIResponse<Page<PostListResponse>>> getAllPost(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<PostListResponse> postList = postService.getAllPost(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.success("게시글 목록 조회 성공", postList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<PostDetailResponse>> getPost(@PathVariable Long id) {
        PostDetailResponse post = postService.getPost(id);
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.success("게시글 조회 성공", post));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<APIResponse<PostDetailResponse>> updatePost(@PathVariable Long id, @RequestBody PostRequestDto dto, HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        PostDetailResponse post = postService.update(dto, id, email);
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.success("게시글 수정 성공", post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        likeService.clearLikes(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<APIResponse<PostCreateResponse>> createPost(@Valid @RequestBody PostRequestDto dto, HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        PostCreateResponse post = postService.createPost(dto, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.success("게시글 작성 성공", post));
    }
}
