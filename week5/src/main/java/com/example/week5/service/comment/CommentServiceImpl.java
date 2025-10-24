package com.example.week5.service.comment;

import com.example.week5.common.AuthValidator;
import com.example.week5.common.exception.custom.ResourceNotFoundException;
import com.example.week5.common.exception.custom.UnauthorizedException;
import com.example.week5.domain.Comment;
import com.example.week5.domain.Post;
import com.example.week5.domain.User;
import com.example.week5.dto.request.comment.CommentRequestDto;
import com.example.week5.dto.response.comment.CommentResponse;
import com.example.week5.repository.comment.CommentRepository;
import com.example.week5.repository.post.PostRepository;
import com.example.week5.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.week5.common.exception.ErrorMessage.*;

@Service
@RequiredArgsConstructor
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

        Comment savedComment = commentRepository.save(comment);
        post.addComment(savedComment);

        return CommentResponse.fromEntity(savedComment);
    }

    @Override
    public List<CommentResponse> getCommentByPost(Long postId) {

        List<Comment> comments = commentRepository.findAllByPostId(postId);
        return comments.stream().map(CommentResponse::fromEntity).toList();
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
        commentRepository.delete(id);
    }
}
