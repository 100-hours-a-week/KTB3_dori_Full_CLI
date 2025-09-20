package domain.audio;
import domain.Media;
import domain.review.Review;


import java.util.Scanner;

public class Music extends Audio {
    public String album;
    public MusicGenre genre;

    @Override
    public Media registerMedia(Scanner sc) {
        super.registerMedia(sc);
        System.out.println("가수를 입력하세요");
        artist = sc.nextLine();

        System.out.println("대표 장르를 입력하세요");
        System.out.println("(POP, ROCK, HIPHOP, JAZZ, SOUL, OST, FORK, COUNTRY, ETC)");
        String gen = sc.nextLine();
        genre = MusicGenre.valueOf(gen.toUpperCase());

        System.out.println("앨범 이름을 입력하세요");
        album = sc.nextLine();

        System.out.println("음악 길이를 입력하세요(0:00)");
        musicLength = sc.nextLine();
        return this;
    }

    @Override
    public void viewDetail() {
        System.out.println("제목: " + title);
        System.out.println("출시연도: " + releaseYear);
        System.out.println("가수: " + artist);
        System.out.println("앨범: " + album);
        System.out.println("음악 길이: " + musicLength);
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
                    + " 작성일: " + review.date.format(FORMATTER));
            i++;
        }
    }
}
