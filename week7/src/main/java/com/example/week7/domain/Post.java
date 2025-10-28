package com.example.week7.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;
    private String title;

    @Lob
    private String content;
    private Long viewCount;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.viewCount = 0L;
        this.user = user;
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        this.modifiedDate = createdDate;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

    public void generateId(Long id) {
        this.id = id;
    }

    // 사용자 등록
    public void setMappingUser(User user) {
        this.user = user;
    }

    public void upViewcount() {
        this.viewCount++;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setMappingPost(this);
    }

    public void deleteComment(Comment comment) {
        comments.remove(comment);
        comment.setMappingPost(null);
    }
}
