package com.example.week7.dto.response.post;

import com.example.week7.common.util.DateTimeUtils;
import com.example.week7.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostDetailResponse {

    private Long postId;
    private String title;
    private String content;
    private String writer;
    private Long viewCount;
    private String createdDate;
    private String modifiedDate;


    @Builder
    public PostDetailResponse(Long postId, String title, String content, String writer, Long viewCount, String createdDate, String modifiedDate) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.viewCount = viewCount;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }


    public static PostDetailResponse fromEntity(Post post) {
        return PostDetailResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getUser().getNickname())
                .viewCount(post.getViewCount())
                .createdDate(DateTimeUtils.format(post.getCreatedDate()))
                .modifiedDate(DateTimeUtils.format(post.getModifiedDate()))
                .build();
    }
}
