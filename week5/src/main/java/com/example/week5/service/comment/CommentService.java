package com.example.week5.service.comment;

import com.example.week5.dto.request.comment.CommentRequestDto;
import com.example.week5.dto.response.comment.CommentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    CommentResponse createComment(CommentRequestDto dto, Long postId, String email);

    List<CommentResponse> getCommentByPost(Long postId);

    CommentResponse getComment(Long id) ;

    CommentResponse update(CommentRequestDto dto, Long id, String email);

    void delete(Long id);
}
