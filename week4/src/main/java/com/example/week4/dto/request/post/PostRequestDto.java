package com.example.week4.dto.request.post;

import com.example.week4.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestDto {

    private String title;
    private String content;

    @Builder
    public PostRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Post toEntity(PostRequestDto dto) {
        return Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }
}
