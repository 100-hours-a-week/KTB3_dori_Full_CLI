package domain.audio;

import domain.Media;

public class Audio extends Media {
    private String artist;
    private String musicLength;

    @Override
    public boolean isMediaSearch(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return this.getTitle().toLowerCase().contains(lowerKeyword)
                || this.getArtist().toLowerCase().contains(lowerKeyword);
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getMusicLength() {
        return musicLength;
    }

    public void setMusicLength(String musicLength) {
        this.musicLength = musicLength;
    }
}
