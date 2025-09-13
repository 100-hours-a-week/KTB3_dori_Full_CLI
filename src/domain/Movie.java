package domain;

import genre.VideoGenre;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Movie extends Video{

    public String director;
    public int length;

    @Override
    public Media registerMedia() {
        super.registerMedia();
        Scanner sc = new Scanner(System.in);
        System.out.println("대표 장르를 입력하세요");
        System.out.println("(ACTION, SF, THRILLER, ROMANCE, ADVENTURE, DRAMA, HORROR, FAMILY, ANIMATION, ETC)");
        String gen = sc.nextLine();
        genre = VideoGenre.valueOf(gen.toUpperCase());


        System.out.println("감독 이름을 입력하세요");
        director = sc.nextLine();

        System.out.println("출연진을 입력하세요(콤마로 구분)");
        String[] split = sc.nextLine().split(",");
        for (String s : split) {
            cast.add(s.trim());
        }

        System.out.println("러닝 타임을 입력하세요(분)");
        length = sc.nextInt();

        return this;
    }

    @Override
    public void viewDetail() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd/HH:mm:ss");
        System.out.println("제목: " + title);
        System.out.println("장르: " + genre);
        System.out.println("출시연도: " + releaseYear);
        System.out.println("감독 이름: " + director);
        System.out.print("출연진: ");
        for (String s : cast) {
            System.out.print(s + ", ");
        }
        System.out.println();
        System.out.println("러닝 타임: " + length + "분");
        System.out.println("메모: " + note);
        System.out.println("=========리뷰==========");
        calcAvgScore();
        System.out.printf("사용자 평균 별점: %.1f \n", calcAvgScore());
        int i = 1;
        for (Review review : reviews) {
            System.out.println(i +". " +
                    "작성자: " + review.writer
                    + " 평점: " + review.score
                    + " 리뷰 내용: " + review.comment
                    + " 작성일: " + review.date.format(formatter));
            i++;
        }
    }
}
