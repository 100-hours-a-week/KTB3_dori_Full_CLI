package com.example.week5.repository.post;

import com.example.week5.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostMapRepository implements PostRepository{

    public static final Map<Long, Post> postMap = new ConcurrentHashMap<>();
    private static AtomicLong postCount = new AtomicLong(0);

    @Override
    public Post save(Post post) {
        post.generateId(postCount.incrementAndGet());
        postMap.put(post.getId(), post);
        return post;
    }

    @Override
    public void delete(Long id) {
        postMap.remove(id);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(postMap.get(id));
    }

    @Override
    public List<Post> findAll() {
        return postMap.values().stream().toList();
    }

}
