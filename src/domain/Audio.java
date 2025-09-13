package domain;

import java.util.Scanner;

public class Audio extends Media{
    public String artist;
    public String length;
    public float avgScore = 0;

    public float calcAvgScore() {
        if (reviews.isEmpty()) return avgScore;
        float sum = 0;
        for (Review review : reviews) {
            sum += review.score;
        }
        avgScore = sum / reviews.size();
        return avgScore;
    }

    @Override
    public boolean search(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return this.title.toLowerCase().contains(lowerKeyword)
                || this.artist.toLowerCase().contains(lowerKeyword);
    }
}
