package com.example.week5.service.post;

import com.example.week5.common.AuthValidator;
import com.example.week5.common.exception.custom.ResourceNotFoundException;
import com.example.week5.common.exception.custom.UnauthorizedException;
import com.example.week5.domain.Post;
import com.example.week5.domain.User;
import com.example.week5.dto.request.post.PostRequestDto;
import com.example.week5.dto.response.post.PostCreateResponse;
import com.example.week5.dto.response.post.PostDetailResponse;
import com.example.week5.dto.response.post.PostListResponse;
import com.example.week5.repository.comment.CommentRepository;
import com.example.week5.repository.post.PostRepository;
import com.example.week5.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.week5.common.exception.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final AuthValidator authValidator;

    @Override
    public PostCreateResponse createPost(PostRequestDto dto, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UnauthorizedException(UNAUTHORIZED));
        Post post = PostRequestDto.ofEntity(dto);

        post.setMappingUser(user);

        Post savedPost = postRepository.save(post);

        return PostCreateResponse.fromEntity(savedPost);
    }

    @Override
    public PostDetailResponse getPost(Long id) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
        post.upViewcount();

        return PostDetailResponse.fromEntity(post);
    }

    @Override
    public PostDetailResponse update(PostRequestDto dto, Long id, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UnauthorizedException(UNAUTHORIZED)
        );

        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(RESOURCE_NOT_FOUND)
        );
        authValidator.validate(user, post.getUser());

        post.update(dto.getTitle(), dto.getContent());

        return PostDetailResponse.fromEntity(post);
    }

    @Override
    public List<PostListResponse> getAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostListResponse::fromEntity).toList();
    }

    @Override
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(RESOURCE_NOT_FOUND)
        );
        post.getComments().forEach((comment) -> commentRepository.delete(comment.getId()));
        post.getComments().clear();
        postRepository.delete(id);
    }
}
