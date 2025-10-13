package com.example.week5.service;

import com.example.week5.common.exception.custom.ResourceNotFoundException;
import com.example.week5.common.exception.custom.UnauthenticatedException;
import com.example.week5.common.exception.custom.UnauthorizedException;
import com.example.week5.domain.Comment;
import com.example.week5.domain.Post;
import com.example.week5.domain.User;
import com.example.week5.dto.request.comment.CommentRequestDto;
import com.example.week5.dto.response.comment.CommentResponse;
import com.example.week5.repository.CommentRepository;
import com.example.week5.repository.PostRepository;
import com.example.week5.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentResponse createComment(CommentRequestDto dto, Long postId, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UnauthorizedException("권한이 없습니다. 로그인 후 이용해주세요")
        );
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("존재하지 않는 페이지입니다.")
        );

        Comment comment = CommentRequestDto.ofEntity(dto);
        comment.setMappingUser(user);

        Comment savedComment = commentRepository.save(comment);
        post.addComment(savedComment);

        return CommentResponse.fromEntity(savedComment);
    }

    public List<CommentResponse> getCommentByPost(Long postId) {

        List<Comment> comments = commentRepository.findAllByPostId(postId);
        return comments.stream().map(CommentResponse::fromEntity).toList();
    }

    public CommentResponse getComment(Long id) {

        Comment comment = commentRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("존재하지 않는 페이지입니다."));

        return CommentResponse.fromEntity(comment);
    }

    public CommentResponse update(CommentRequestDto dto, Long id, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UnauthorizedException("권한이 없습니다. 로그인 후 이용해주세요")
        );

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("존재하지 않는 페이지입니다")
        );

        if (!user.getId().equals(comment.getUser().getId())) {
            throw new UnauthenticatedException("권한이 없습니다.");
        }

        comment.update(dto.getContent());
        return CommentResponse.fromEntity(comment);
    }


    public void delete(Long id) {
        commentRepository.delete(id);
    }
}
