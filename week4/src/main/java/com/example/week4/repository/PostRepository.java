package com.example.week4.repository;

import com.example.week4.domain.Post;
import com.example.week4.domain.User;
import com.example.week4.dto.request.post.PostRequestDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class PostRepository {

    public static final Map<Long, Post> postMap = new LinkedHashMap<>();
    private static long postCount = 0L;

    public Post save(Post post) {
        post.generateId(++postCount);
        postMap.put(post.getId(), post);
        return post;
    }
    public Iterable<Post> findAll() {
        return postMap.values();
    }
    
    public void delete(Long id) {
        postMap.remove(id);
    }

    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(postMap.get(id));
    }


}
