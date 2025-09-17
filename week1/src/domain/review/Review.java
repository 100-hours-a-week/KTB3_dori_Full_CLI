package domain.review;

import java.time.LocalDateTime;

public class Review {

    public String writer;
    public int score;
    public String comment;
    public LocalDateTime date;

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
}
