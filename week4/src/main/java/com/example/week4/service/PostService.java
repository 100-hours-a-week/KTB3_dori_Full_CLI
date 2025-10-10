package com.example.week4.service;

import com.example.week4.common.exception.custom.ResourceNotFoundException;
import com.example.week4.common.exception.custom.UnauthenticatedException;
import com.example.week4.common.exception.custom.UnauthorizedException;
import com.example.week4.domain.Post;
import com.example.week4.domain.User;
import com.example.week4.dto.request.post.PostRequestDto;
import com.example.week4.dto.response.post.PostCreateResponse;
import com.example.week4.dto.response.post.PostDetailResponse;
import com.example.week4.dto.response.post.PostListResponse;
import com.example.week4.repository.PostRepository;
import com.example.week4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostCreateResponse createPost(PostRequestDto dto, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UnauthorizedException("권한이 없습니다. 로그인 후 이용하세요"));
        Post post = PostRequestDto.ofEntity(dto);

        post.setMappingUser(user);

        Post savedPost = postRepository.save(post);

        return PostCreateResponse.fromEntity(savedPost);
    }

    public PostDetailResponse getPost(Long id) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("게시글이 존재하지 않습니다."));
        post.upViewcount();

        return PostDetailResponse.fromEntity(post);
    }

    public PostDetailResponse update(PostRequestDto dto, Long id, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UnauthorizedException("권한이 없습니다. 로그인 후 이용해주세요"));
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 페이지입니다"));
        if (!user.equals(post.getUser())) {
            throw new UnauthenticatedException("권한이 없습니다");
        }
        post.update(dto.getTitle(), dto.getContent());
        Post update = postRepository.update(id, post);

        return PostDetailResponse.fromEntity(update);
    }

    public List<PostListResponse> getAllPost() {
        List<Post> postList = postRepository.findAll();
        return postList.stream().map(PostListResponse::fromEntity).toList();
    }

    public void delete(Long id) {
        postRepository.delete(id);
    }
}
