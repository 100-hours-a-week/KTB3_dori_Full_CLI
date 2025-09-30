package test;

import domain.audio.Music;
import domain.audio.MusicGenre;
import domain.review.Review;
import domain.video.VideoGenre;
import domain.video.Drama;
import domain.video.Movie;
import service.MediaTask;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static void loadTest(MediaTask mediaTask) {
        // 영화 데이터
        Movie movie1 = new Movie();
        movie1.setTitle("인셉션");
        movie1.setReleaseYear(2010);
        movie1.setGenre(VideoGenre.SF);
        movie1.setDirector("크리스토퍼 놀란");
        movie1.setRunningTime(148);
        movie1.setCast(new ArrayList<>(List.of("레오나르도 디카프리오", "조셉 고든-레빗")));
        movie1.setNote("꿈");
        movie1.setReviews(new ArrayList<>(List.of(
                new Review("A", 9, "good"),
                new Review("B", 8, "good")
        )));
        mediaTask.addVideo(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("인터스텔라");
        movie2.setReleaseYear(2014);
        movie2.setGenre(VideoGenre.SF);
        movie2.setDirector("크리스토퍼 놀란");
        movie2.setRunningTime(169);
        movie2.setCast(new ArrayList<>(List.of("매튜 매커너히", "앤 해서웨이")));
        movie2.setNote("우주");
        movie2.setReviews(new ArrayList<>(List.of(
                new Review("C", 10, "good"),
                new Review("D", 10, "good"),
                new Review("E", 8, "good")
        )));
        mediaTask.addVideo(movie2);

        Movie movie3 = new Movie();
        movie3.setTitle("다크 나이트");
        movie3.setReleaseYear(2008);
        movie3.setGenre(VideoGenre.ACTION);
        movie3.setDirector("크리스토퍼 놀란");
        movie3.setRunningTime(152);
        movie3.setCast(new ArrayList<>(List.of("크리스찬 베일", "히스 레저")));
        movie3.setNote("배트맨");
        movie3.setReviews(new ArrayList<>(List.of(
                new Review("F", 10, "good"),
                new Review("G", 10, "good")
        )));
        mediaTask.addVideo(movie3);

        // 드라마 데이터
        Drama drama1 = new Drama();
        drama1.setTitle("브레이킹 배드");
        drama1.setReleaseYear(2008);
        drama1.setGenre(VideoGenre.DRAMA);
        drama1.setSeason(5);
        drama1.setEpisode(62);
        drama1.setCast(new ArrayList<>(List.of("브라이언 크랜스턴", "애런 폴")));
        drama1.setNote("화학");
        drama1.setReviews(new ArrayList<>(List.of(
                new Review("H", 10, "good")
        )));
        mediaTask.addVideo(drama1);

        Drama drama2 = new Drama();
        drama2.setTitle("왕좌의 게임");
        drama2.setReleaseYear(2011);
        drama2.setGenre(VideoGenre.DRAMA);
        drama2.setSeason(8);
        drama2.setEpisode(73);
        drama2.setCast(new ArrayList<>(List.of("키트 해링턴", "에밀리아 클라크")));
        drama2.setNote("판타지");
        drama2.setReviews(new ArrayList<>(List.of(
                new Review("I", 8, "good"),
                new Review("J", 6, "good")
        )));
        mediaTask.addVideo(drama2);

        // 음악 데이터
        Music music1 = new Music();
        music1.setTitle("Shape of You");
        music1.setArtist("Ed Sheeran");
        music1.setAlbum("Divide");
        music1.setMusicLength("3:53");
        music1.setGenre(MusicGenre.POP);
        music1.setReleaseYear(2017);
        music1.setNote("빌보드 1위");
        music1.setReviews(new ArrayList<>(List.of(
                new Review("K", 10, "good"),
                new Review("L", 8, "good")
        )));
        mediaTask.addMusic(music1);

        Music music2 = new Music();
        music2.setTitle("Perfect");
        music2.setArtist("Ed Sheeran");
        music2.setAlbum("Divide");
        music2.setMusicLength("4:23");
        music2.setGenre(MusicGenre.POP);
        music2.setReleaseYear(2017);
        music2.setNote("발라드");
        music2.setReviews(new ArrayList<>(List.of(
                new Review("M", 8, "good")
        )));
        mediaTask.addMusic(music2);

        Music music3 = new Music();
        music3.setTitle("Bohemian Rhapsody");
        music3.setArtist("Queen");
        music3.setAlbum("A Night at the Opera");
        music3.setMusicLength("5:55");
        music3.setGenre(MusicGenre.ROCK);
        music3.setReleaseYear(1975);
        music3.setNote("명곡");
        music3.setReviews(new ArrayList<>(List.of(
                new Review("N", 10, "good"),
                new Review("O", 10, "good"),
                new Review("P", 10, "good")
        )));
        mediaTask.addMusic(music3);

        Music music4 = new Music();
        music4.setTitle("Bad Guy");
        music4.setArtist("Billie Eilish");
        music4.setAlbum("When We All Fall Asleep, Where Do We Go?");
        music4.setMusicLength("3:14");
        music4.setGenre(MusicGenre.POP);
        music4.setReleaseYear(2019);
        music4.setNote("빌보드 히트");
        music4.setReviews(new ArrayList<>(List.of(
                new Review("Q", 6, "good"),
                new Review("R", 4, "good")
        )));
        mediaTask.addMusic(music4);
    }
}