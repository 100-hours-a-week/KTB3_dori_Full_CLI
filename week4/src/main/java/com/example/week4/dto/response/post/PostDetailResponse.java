package com.example.week4.dto.response.post;

import com.example.week4.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDetailResponse {

    private Long postId;
    private String title;
    private String content;
    private String writer;
    private Long viewCount;
//    private String createdAt;
//    private List<CommentResponse> comments = new ArrayList<>();
    //heart
    // commentCount

    @Builder
    public PostDetailResponse(Long postId, String title, String content, String writer, Long viewCount) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.viewCount = viewCount;
    }

    public static PostDetailResponse fromEntity(Post post) {
        return PostDetailResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getUser().getNickname())
                .viewCount(post.getViewCount())
                .build();
    }
}
