package com.example.week4.repository;

import com.example.week4.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
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
    
    public void delete(Long id) {
        postMap.remove(id);
    }

    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(postMap.get(id));
    }

    public List<Post> findAll() {
        return postMap.values().stream().toList();
    }

}
