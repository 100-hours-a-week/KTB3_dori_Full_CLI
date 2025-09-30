package domain.video;


import domain.Media;

import java.util.ArrayList;
import java.util.List;

public class Video extends Media {

    private List<String> cast = new ArrayList<>();
    private VideoGenre genre;

    @Override
    public boolean isMediaSearch(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        if (this.getTitle().toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        for (String actor : cast) {
            if (actor.toLowerCase().contains(lowerKeyword))
                return true;
        }

        return false;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public VideoGenre getGenre() {
        return genre;
    }

    public void setGenre(VideoGenre genre) {
        this.genre = genre;
    }
}
