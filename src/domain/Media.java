package domain;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class Media {
    public String title;
    public int releaseYear;
    public String note;
    public List<Review> reviews = new ArrayList<>();


    public Media() {
    }

    public Media(String title, int releaseYear, String note, List<Review> reviews) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.note = note;
        this.reviews = reviews;
    }

    public Media registerMedia() {
        Scanner sc = new Scanner(System.in);

        System.out.println("미디어 제목을 입력하세요");
        title = sc.nextLine();

        System.out.println("출시 연도를 입력하세요");
        releaseYear = sc.nextInt();

        System.out.println("메모를 입력하세요");
        sc.nextLine();
        note = sc.nextLine();

        return this;
    }

    public void viewList() {
        System.out.println("제목: " + title +
                " / 메모: " + note +
                " / 출시연도: " + releaseYear + "년" +
                " / 카테고리: " + this.getClass().getSimpleName());
    }

    public void viewDetail() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd/HH:mm:ss");
        System.out.println("제목: " + title);
        System.out.println("출시연도: " + releaseYear);
        System.out.println("메모: " + note);
        System.out.println("=========리뷰==========");
        int i = 1;
        for (Review review : reviews) {
            System.out.println(i +". "
                    + "작성자: " + review.writer
                    + " 평점: " + review.score
                    + " 리뷰 내용: " + review.comment
                    + " 작성일: " + review.date.format(formatter));
        }
    }

    public void edit() {
        Scanner sc = new Scanner(System.in);

        System.out.println("수정할 제목을 입력하세요");
        title = sc.nextLine();


    }

    public void addReview() {
        Scanner sc = new Scanner(System.in);
        System.out.println("작성자 이름을 입력하세요");
        String writer = sc.nextLine();

        System.out.println("한줄평을 입력하세요: ");
        String comment = sc.nextLine();

        System.out.println("평점을 입력하세요(1 ~ 10)");
        int score = sc.nextInt();

        Review review = new Review(writer, score, comment);

        reviews.add(review);
    }

    public boolean search(String keyword) {
        return false;
    }
}
