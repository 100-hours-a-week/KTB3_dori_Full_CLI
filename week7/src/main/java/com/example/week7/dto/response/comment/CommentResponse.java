package com.example.week7.dto.response.comment;

import com.example.week7.common.util.DateTimeUtils;
import com.example.week7.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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
                .createdDate(DateTimeUtils.format(comment.getCreatedDate()))
                .modifiedDate(DateTimeUtils.format(comment.getModifiedDate()))
                .build();
    }
}
