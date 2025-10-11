package com.example.week4.controller;

import com.example.week4.common.response.APIResponse;
import com.example.week4.dto.request.comment.CommentRequestDto;
import com.example.week4.dto.response.comment.CommentResponse;
import com.example.week4.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

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
    public ResponseEntity<APIResponse<List<CommentResponse>>> getComments(
            @PathVariable Long postId
    ) {
        List<CommentResponse> comments = commentService.getCommentByPost(postId);
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
