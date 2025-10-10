package com.example.week4.repository;

import com.example.week4.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class CommentRepository {

    private static final Map<Long, Comment> commentMap = new LinkedHashMap<>();
    private static Long commentCount = 0L;

    public Comment save(Comment comment) {
        comment.generateId(++commentCount);
        commentMap.put(comment.getId(), comment);
        return comment;
    }

    public void delete(Long id) {
        commentMap.remove(id);
    }


}
