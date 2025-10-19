package com.example.week5.repository.post;

import com.example.week5.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository {

    public Post save(Post post);

    public void delete(Long id);

    public Optional<Post> findById(Long id);

    public List<Post> findAll();
}
