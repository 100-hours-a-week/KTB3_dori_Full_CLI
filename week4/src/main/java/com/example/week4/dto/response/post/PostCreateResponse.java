package com.example.week4.dto.response.post;

import com.example.week4.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostCreateResponse {
    private Long postId;
    private String title;
    private String writer;
    private String createdDate;

    @Builder
    public PostCreateResponse(Long postId, String title, String writer, String createdDate) {
        this.postId = postId;
        this.title = title;
        this.writer = writer;
        this.createdDate = createdDate;
    }

    public static PostCreateResponse fromEntity(Post post) {
        return PostCreateResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .writer(post.getUser().getNickname())
                .createdDate(post.getCreatedDate())
                .build();
    }
}
