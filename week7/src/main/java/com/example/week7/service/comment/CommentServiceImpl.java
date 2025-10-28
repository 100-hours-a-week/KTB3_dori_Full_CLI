package com.example.week7.service.comment;

import com.example.week7.common.AuthValidator;
import com.example.week7.common.exception.custom.ResourceNotFoundException;
import com.example.week7.common.exception.custom.UnauthorizedException;
import com.example.week7.domain.Comment;
import com.example.week7.domain.Post;
import com.example.week7.domain.User;
import com.example.week7.dto.request.comment.CommentRequestDto;
import com.example.week7.dto.response.comment.CommentResponse;
import com.example.week7.repository.comment.CommentRepository;
import com.example.week7.repository.post.PostRepository;
import com.example.week7.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.week7.common.exception.ErrorMessage.*;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final AuthValidator authValidator;

    @Override
    public CommentResponse createComment(CommentRequestDto dto, Long postId, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UnauthorizedException(UNAUTHORIZED)
        );
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(RESOURCE_NOT_FOUND)
        );

        Comment comment = CommentRequestDto.ofEntity(dto);
        comment.setMappingUser(user);
        comment.setMappingPost(post);

        Comment savedComment = commentRepository.save(comment);

        return CommentResponse.fromEntity(savedComment);
    }

    @Override
    public Page<CommentResponse> getCommentByPost(Long postId, Pageable pageable) {

        Page<Comment> comments = commentRepository.findAllByPostId(postId, pageable);
        return comments.map(CommentResponse::fromEntity);
    }

    @Override
    public CommentResponse getComment(Long id) {

        Comment comment = commentRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));

        return CommentResponse.fromEntity(comment);
    }

    @Override
    public CommentResponse update(CommentRequestDto dto, Long id, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UnauthorizedException(UNAUTHORIZED)
        );

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(RESOURCE_NOT_FOUND)
        );
        authValidator.validate(user, comment.getUser());

        comment.update(dto.getContent());
        return CommentResponse.fromEntity(comment);
    }

    @Override
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(RESOURCE_NOT_FOUND)
        );
        comment.getPost().deleteComment(comment);
        commentRepository.deleteById(id);
    }
}
