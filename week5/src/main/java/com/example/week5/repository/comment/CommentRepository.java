package com.example.week5.repository.comment;

import com.example.week5.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CommentRepository {

    public Comment save(Comment comment);

    public List<Comment> findAllByPostId(Long postId);

    public Optional<Comment> findById(Long id);

    public void delete(Long id);
}
