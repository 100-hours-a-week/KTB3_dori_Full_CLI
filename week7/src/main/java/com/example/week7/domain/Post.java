package com.example.week7.domain;

import com.example.week7.common.BasicTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Post extends BasicTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    private Long viewCount;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.viewCount = 0L;
        this.user = user;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


    public void setMappingUser(User user) {
        this.user = user;
    }

    public void upViewcount() {
        this.viewCount++;
    }


    public void deleteComment(Comment comment) {
        comments.remove(comment);
        comment.setMappingPost(null);
    }
}
