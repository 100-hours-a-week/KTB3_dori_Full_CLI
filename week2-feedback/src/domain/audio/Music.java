package domain.audio;
import domain.Media;
import domain.review.Review;


import java.util.Scanner;

public class Music extends Audio {
    private String album;
    private MusicGenre genre;

    @Override
    public Media registerMedia(Scanner sc) {
        super.registerMedia(sc);
        System.out.println("가수를 입력하세요");
        this.setArtist(sc.nextLine());

        System.out.println("대표 장르를 입력하세요");
        System.out.println("(POP, ROCK, HIPHOP, JAZZ, SOUL, OST, FORK, COUNTRY, ETC)");
        String gen = sc.nextLine();
        genre = MusicGenre.valueOf(gen.toUpperCase());

        System.out.println("앨범 이름을 입력하세요");
        album = sc.nextLine();

        System.out.println("음악 길이를 입력하세요(0:00)");
        this.setMusicLength(sc.nextLine());
        return this;
    }

    @Override
    public void viewDetail() {
        System.out.println("제목: " + this.getTitle());
        System.out.println("출시연도: " + this.getReleaseYear());
        System.out.println("가수: " + this.getArtist());
        System.out.println("앨범: " + album);
        System.out.println("음악 길이: " + this.getMusicLength());
        System.out.println("메모: " + this.getNote());
        System.out.println("=========리뷰==========");
        super.viewDetail();
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }
}
