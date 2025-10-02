package com.example.week4.dto.response.comment;

import com.example.week4.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponse {
    private String content;

    @Builder
    public CommentResponse(String content) {
        this.content = content;
    }

    public static CommentResponse fromEntity(Comment comment) {
        return CommentResponse.builder()
                .content(comment.getContent())
                .build();
    }
}
