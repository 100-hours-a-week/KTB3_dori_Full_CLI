package com.example.week5.dto.response.comment;

import com.example.week5.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponse {
    private Long commentId;
    private String content;
    private String writer;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public CommentResponse(Long commentId, String content, String writer, String createdDate, String modifiedDate) {
        this.commentId = commentId;
        this.content = content;
        this.writer = writer;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }


    public static CommentResponse fromEntity(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .writer(comment.getUser().getNickname())
                .createdDate(comment.getCreatedDate())
                .modifiedDate(comment.getModifiedDate())
                .build();
    }
}
