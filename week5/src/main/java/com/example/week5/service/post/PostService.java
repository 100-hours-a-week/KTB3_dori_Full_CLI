package com.example.week5.service.post;

import com.example.week5.dto.request.post.PostRequestDto;
import com.example.week5.dto.response.post.PostCreateResponse;
import com.example.week5.dto.response.post.PostDetailResponse;
import com.example.week5.dto.response.post.PostListResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    PostCreateResponse createPost(PostRequestDto dto, String email);

    PostDetailResponse getPost(Long id);

    PostDetailResponse update(PostRequestDto dto, Long id, String email);

    List<PostListResponse> getAllPost();

    void delete(Long id);
}
