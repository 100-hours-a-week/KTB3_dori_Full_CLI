package com.example.week4.dto.request.comment;

import com.example.week4.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDto {

    private String content;

    @Builder
    public CommentRequestDto(String content) {
        this.content = content;
    }

    public static Comment toEntity(CommentRequestDto dto) {
        return Comment.builder()
                .content(dto.getContent())
                .build();
    }
}
