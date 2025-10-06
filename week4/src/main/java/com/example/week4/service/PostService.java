package com.example.week4.service;

import com.example.week4.common.exception.custom.ResourceNotFoundException;
import com.example.week4.domain.Post;
import com.example.week4.dto.response.post.PostDetailResponse;
import com.example.week4.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostDetailResponse getPost(Long id) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("게시글이 존재하지 않습니다."));

        return PostDetailResponse.fromEntity(post);
    }

    public void delete(Long id) {
        postRepository.delete(id);
    }
}
