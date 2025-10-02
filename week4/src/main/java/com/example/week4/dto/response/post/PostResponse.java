package com.example.week4.dto.response.post;

import com.example.week4.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String title;

    @Builder
    public PostResponse(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public static PostResponse fromEntity(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .build();
    }
}
