package test;

import domain.audio.Music;
import domain.audio.MusicGenre;
import domain.review.Review;
import domain.video.VideoGenre;
import domain.video.Drama;
import domain.video.Movie;
import service.MediaTask;

public class TestData {

    public static void loadTest(MediaTask mediaTask) {
        // 영화 데이터
        Movie movie1 = new Movie();
        movie1.title = "인셉션";
        movie1.releaseYear = 2010;
        movie1.genre = VideoGenre.SF;
        movie1.director = "크리스토퍼 놀란";
        movie1.runningTime = 148;
        movie1.cast.add("레오나르도 디카프리오");
        movie1.cast.add("조셉 고든-레빗");
        movie1.note = "꿈";
        movie1.reviews.add(new Review("A", 9, "good"));
        movie1.reviews.add(new Review("B", 8, "good"));
        mediaTask.addVideo(movie1);

        Movie movie2 = new Movie();
        movie2.title = "인터스텔라";
        movie2.releaseYear = 2014;
        movie2.genre = VideoGenre.SF;
        movie2.director = "크리스토퍼 놀란";
        movie2.runningTime = 169;
        movie2.cast.add("매튜 매커너히");
        movie2.cast.add("앤 해서웨이");
        movie2.note = "우주";
        movie2.reviews.add(new Review("C", 10, "good"));
        movie2.reviews.add(new Review("D", 10, "good"));
        movie2.reviews.add(new Review("E", 8, "good"));
        mediaTask.addVideo(movie2);

        Movie movie3 = new Movie();
        movie3.title = "다크 나이트";
        movie3.releaseYear = 2008;
        movie3.genre = VideoGenre.ACTION;
        movie3.director = "크리스토퍼 놀란";
        movie3.runningTime = 152;
        movie3.cast.add("크리스찬 베일");
        movie3.cast.add("히스 레저");
        movie3.note = "배트맨";
        movie3.reviews.add(new Review("F", 10, "good"));
        movie3.reviews.add(new Review("G", 10, "good"));
        mediaTask.addVideo(movie3);

        // 드라마 데이터
        Drama drama1 = new Drama();
        drama1.title = "브레이킹 배드";
        drama1.releaseYear = 2008;
        drama1.genre = VideoGenre.DRAMA;
        drama1.season = 5;
        drama1.episode = 62;
        drama1.cast.add("브라이언 크랜스턴");
        drama1.cast.add("애런 폴");
        drama1.note = "화학";
        drama1.reviews.add(new Review("H", 10, "good"));
        mediaTask.addVideo(drama1);

        Drama drama2 = new Drama();
        drama2.title = "왕좌의 게임";
        drama2.releaseYear = 2011;
        drama2.genre = VideoGenre.DRAMA;
        drama2.season = 8;
        drama2.episode = 73;
        drama2.cast.add("키트 해링턴");
        drama2.cast.add("에밀리아 클라크");
        drama2.note = "판타지";
        drama2.reviews.add(new Review("I", 8, "good"));
        drama2.reviews.add(new Review("J", 6, "good"));
        mediaTask.addVideo(drama2);

        // 음악 데이터
        Music music1 = new Music();
        music1.title = "Shape of You";
        music1.artist = "Ed Sheeran";
        music1.album = "Divide";
        music1.musicLength = "3:53";
        music1.genre = MusicGenre.POP;
        music1.releaseYear = 2017;
        music1.note = "빌보드 1위";
        music1.reviews.add(new Review("K", 10, "good"));
        music1.reviews.add(new Review("L", 8, "good"));
        mediaTask.addMusic(music1);

        Music music2 = new Music();
        music2.title = "Perfect";
        music2.artist = "Ed Sheeran";
        music2.album = "Divide";
        music2.musicLength = "4:23";
        music2.genre = MusicGenre.POP;
        music2.releaseYear = 2017;
        music2.note = "발라드";
        music2.reviews.add(new Review("M", 8, "good"));
        mediaTask.addMusic(music2);

        Music music3 = new Music();
        music3.title = "Bohemian Rhapsody";
        music3.artist = "Queen";
        music3.album = "A Night at the Opera";
        music3.musicLength = "5:55";
        music3.genre = MusicGenre.ROCK;
        music3.releaseYear = 1975;
        music3.note = "명곡";
        music3.reviews.add(new Review("N", 10, "good"));
        music3.reviews.add(new Review("O", 10, "good"));
        music3.reviews.add(new Review("P", 10, "good"));
        mediaTask.addMusic(music3);

        Music music4 = new Music();
        music4.title = "Bad Guy";
        music4.artist = "Billie Eilish";
        music4.album = "When We All Fall Asleep, Where Do We Go?";
        music4.musicLength = "3:14";
        music4.genre = MusicGenre.POP;
        music4.releaseYear = 2019;
        music4.note = "빌보드 히트";
        music4.reviews.add(new Review("Q", 6, "good"));
        music4.reviews.add(new Review("R", 4, "good"));
        mediaTask.addMusic(music4);
    }
}