package domain.video;

import domain.Media;
import domain.review.Review;

import java.util.Scanner;

public class Movie extends Video{

    private String director;
    private int runningTime;

    @Override
    public Media registerMedia(Scanner sc) {
        super.registerMedia(sc);
        System.out.println("대표 장르를 입력하세요");
        System.out.println("(ACTION, SF, THRILLER, ROMANCE, ADVENTURE, DRAMA, HORROR, FAMILY, ANIMATION, ETC)");
        String gen = sc.nextLine();
        this.setGenre(VideoGenre.valueOf(gen.toUpperCase()));


        System.out.println("감독 이름을 입력하세요");
        director = sc.nextLine();

        System.out.println("출연진을 입력하세요(콤마로 구분)");
        String[] split = sc.nextLine().split(",");
        for (String s : split) {
            this.getCast().add(s.trim());
        }

        System.out.println("러닝 타임을 입력하세요(분)");
        runningTime = sc.nextInt();

        return this;
    }

    @Override
    public void viewDetail() {
        System.out.println("제목: " + this.getTitle());
        System.out.println("장르: " + this.getGenre());
        System.out.println("출시연도: " + this.getReleaseYear());
        System.out.println("감독 이름: " + director);
        System.out.print("출연진: ");
        for (String s : this.getCast()) {
            System.out.print(s + ", ");
        }
        System.out.println();
        System.out.println("러닝 타임: " + runningTime + "분");
        System.out.println("메모: " + this.getNote());
        super.viewDetail();
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }
}
