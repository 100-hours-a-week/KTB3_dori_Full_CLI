package test;

import domain.*;
import genre.MusicGenre;
import genre.VideoGenre;

public class TestData {

    public static void loadTest(MediaTask mediaTask) {
        Movie movie1 = new Movie();
        movie1.title = "인셉션";
        movie1.releaseYear = 2010;
        movie1.genre = VideoGenre.SF;
        movie1.director = "크리스토퍼 놀란";
        movie1.length = 148;
        movie1.cast.add("레오나르도 디카프리오");
        movie1.cast.add("조셉 고든-레빗");
        movie1.cast.add("Joseph");
        movie1.cast.add("joseph");
        movie1.note = "꿈";
        movie1.reviews.add(new Review("A", 5, "good"));
        movie1.reviews.add(new Review("B", 4, "good"));
        mediaTask.addVideo(movie1);

        Drama drama1 = new Drama();
        drama1.title = "브레이킹 배드";
        drama1.releaseYear = 2008;
        drama1.genre = VideoGenre.DRAMA;
        drama1.season = 5;
        drama1.episode = 62;
        drama1.cast.add("브라이언 크랜스턴");
        drama1.cast.add("애런 폴");
        drama1.cast.add("Joseph");
        drama1.cast.add("joseph");
        drama1.note = "화학";
        drama1.reviews.add(new Review("C", 5, "good"));
        mediaTask.addVideo(drama1);

        Music music1 = new Music();
        music1.title = "Shape of You";
        music1.artist = "Ed Sheeran";
        music1.album = "Divide";
        music1.length = "3:53";
        music1.genre = MusicGenre.POP;
        music1.releaseYear = 2017;
        music1.note = "빌보드 1위gi";
        music1.reviews.add(new Review("D", 5, "good"));
        music1.reviews.add(new Review("E", 4, "good"));
        mediaTask.addMusic(music1);

        Music music2 = new Music();
        music2.title = "Perfect";
        music2.artist = "Ed Sheeran";
        music2.album = "Divide";
        music2.length = "4:23";
        music2.genre = MusicGenre.POP;
        music2.releaseYear = 2017;
        music2.note = "감성 발라드";
        music2.reviews.add(new Review("F", 4, "good"));
        mediaTask.addMusic(music2);
    }
}