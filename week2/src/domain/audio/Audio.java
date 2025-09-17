package domain.audio;

import domain.Media;
import domain.review.Review;

public class Audio extends Media {
    public String artist;
    public String musicLength;

    @Override
    public boolean isMediaSearch(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return this.title.toLowerCase().contains(lowerKeyword)
                || this.artist.toLowerCase().contains(lowerKeyword);
    }
}
