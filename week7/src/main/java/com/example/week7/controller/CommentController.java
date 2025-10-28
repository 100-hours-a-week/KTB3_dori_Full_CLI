package com.example.week7.controller;

import com.example.week7.common.response.APIResponse;
import com.example.week7.dto.request.comment.CommentRequestDto;
import com.example.week7.dto.response.comment.CommentResponse;
import com.example.week7.service.comment.CommentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentService;

    @PostMapping
    public ResponseEntity<APIResponse<CommentResponse>> createComment(
            @PathVariable Long postId,
            @RequestBody CommentRequestDto dto,
            HttpServletRequest request
    ) {
        String email = (String) request.getAttribute("email");
        CommentResponse comment = commentService.createComment(dto, postId, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.success("댓글 작성 성공", comment));
    }

    @GetMapping
    public ResponseEntity<APIResponse<Page<CommentResponse>>> getComments(
            @PathVariable Long postId,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable

    ) {
        Page<CommentResponse> comments = commentService.getCommentByPost(postId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.success("댓글 조회 성공", comments));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<APIResponse<CommentResponse>> updateComment(
            @PathVariable Long postId,
            @PathVariable Long id,
            @RequestBody CommentRequestDto dto,
            HttpServletRequest request
    ) {
        String email = (String) request.getAttribute("email");
        CommentResponse comment = commentService.update(dto, id, email);

        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.success("댓글 수정 성공", comment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteComment(@PathVariable Long postId, @PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
