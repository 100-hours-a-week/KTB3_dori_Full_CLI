package com.example.week5.dto.response.post;

import com.example.week5.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostListResponse {

    private Long postId;
    private String title;
    private String content;
    private String writer;
    private Long viewCount;
//    private String createdAt;
//    commentCount
//    heart

    @Builder
    public PostListResponse(Long postId, String title, String content, String writer, Long viewCount) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.viewCount = viewCount;
    }


    public static PostListResponse fromEntity(Post post) {
        return PostListResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getUser().getNickname())
                .viewCount(post.getViewCount())
                .build();
    }
}
