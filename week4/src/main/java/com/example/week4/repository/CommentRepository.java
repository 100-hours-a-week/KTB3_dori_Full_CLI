package com.example.week4.repository;

import com.example.week4.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CommentRepository {

    private static final Map<Long, Comment> commentMap = new LinkedHashMap<>();
    private static Long commentCount = 0L;

    public Comment save(Comment comment) {
        comment.generateId(++commentCount);
        commentMap.put(comment.getId(), comment);
        return comment;
    }

    public Comment update(Long id, Comment comment) {
        commentMap.put(id, comment);
        return comment;
    }

    public List<Comment> findAllByPostId(Long postId) {
        return commentMap.values().stream()
                .filter(comment -> comment.getPost().getId().equals(postId))
                .toList();
    }

    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(commentMap.get(id));
    }

    public void delete(Long id) {
        commentMap.remove(id);
    }


}
