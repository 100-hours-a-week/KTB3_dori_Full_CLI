package com.example.week5.repository.comment;

import com.example.week5.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CommentMapRepository implements CommentRepository {

    private static final Map<Long, Comment> commentMap = new ConcurrentHashMap<>();
    private static AtomicLong commentCount = new AtomicLong(0);

    @Override
    public Comment save(Comment comment) {
        comment.generateId(commentCount.incrementAndGet());
        commentMap.put(comment.getId(), comment);
        return comment;
    }

    @Override
    public List<Comment> findAllByPostId(Long postId) {
        return commentMap.values().stream()
                .filter(comment -> comment.getPost().getId().equals(postId))
                .toList();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(commentMap.get(id));
    }

    @Override
    public void delete(Long id) {
        commentMap.remove(id);
    }


}
