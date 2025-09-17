package domain.video;


import domain.Media;
import domain.review.Review;

import java.util.ArrayList;
import java.util.List;

public class Video extends Media {

    public List<String> cast = new ArrayList<>();
    public float avgScore = 0;
    public VideoGenre genre;

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
    public boolean isMediaSearch(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        if (this.title.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        for (String actor : cast) {
            if (actor.toLowerCase().contains(lowerKeyword))
                return true;
        }

        return false;
    }
}
