package domain.review;

import java.time.LocalDateTime;

public class Review {

    private String writer;
    private int score;
    private String comment;
    private LocalDateTime date;

    public Review() {
    }

    public Review(String writer, int score, String comment) {
        this.writer = writer;
        this.score = score;
        this.comment = comment;
        date = LocalDateTime.now();
    }

    public Review(String writer, String comment) {
        this.writer = writer;
        this.comment = comment;
        date = LocalDateTime.now();
    }

    public String getWriter() {
        return writer;
    }

    public int getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
