package domain.video;

import domain.Media;
import domain.review.Review;

import java.util.Scanner;

public class Drama extends Video{
    private int season;
    private int episode;

    @Override
    public Media registerMedia(Scanner sc) {
        super.registerMedia(sc);
        System.out.println("대표 장르를 입력하세요");
        System.out.println("(ACTION, SF, THRILLER, ROMANCE, ADVENTURE, DRAMA, HORROR, FAMILY, ANIMATION, ETC)");
        String gen = sc.nextLine();
        this.setGenre(VideoGenre.valueOf(gen.toUpperCase()));

        System.out.println("출연진을 입력하세요(콤마로 구분)");
        String[] split = sc.nextLine().split(",");
        for (String s : split) {
            this.getCast().add(s.trim());
        }
        System.out.println("시즌을 입력하세요(정수)");
        season = sc.nextInt();

        System.out.println("에피소드 갯수를 입력하세요(정수)");
        episode = sc.nextInt();


        return this;
    }

    @Override
    public void viewDetail() {
        System.out.println("제목: " + this.getTitle());
        System.out.println("출시연도: " + this.getReleaseYear());
        System.out.println("시즌: " + season);
        System.out.println("에피소드 수: " + episode);
        System.out.print("출연진: ");
        for (String s : this.getCast()) {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("메모: " + this.getNote());
        super.viewDetail();
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }
}
