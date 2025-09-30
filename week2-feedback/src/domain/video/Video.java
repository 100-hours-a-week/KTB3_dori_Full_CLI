package domain.video;


import domain.Media;

import java.util.ArrayList;
import java.util.List;

public class Video extends Media {

    public List<String> cast = new ArrayList<>();
    public VideoGenre genre;

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
