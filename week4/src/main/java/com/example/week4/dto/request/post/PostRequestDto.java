package com.example.week4.dto.request.post;

import com.example.week4.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestDto {

    @NotBlank(message = "제목을 작성해주세요")
    private String title;

    @NotBlank(message = "내용을 작성해주새요")
    private String content;

    @Builder
    public PostRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Post ofEntity(PostRequestDto dto) {
        return Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }
}
