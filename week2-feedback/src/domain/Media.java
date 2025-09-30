package domain;

import domain.review.Review;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class Media {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd/HH:mm:ss");

    private String title;
    private int releaseYear;
    private String note;
    private float avgScore = 0;
    private List<Review> reviews = new ArrayList<>();


    public Media() {
    }

    public Media(String title, int releaseYear, String note, List<Review> reviews) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.note = note;
        this.reviews = reviews;
    }

    public Media registerMedia(Scanner sc) {
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
        System.out.printf("제목: %s / 메모: %s / 출시연도: %d년 / 카테고리: %s / 사용자 평균 별점: %.1f\n",
                title, note, releaseYear, this.getClass().getSimpleName(), calcAvgScore());
    }

    public void viewDetail() {
        System.out.println("=========리뷰==========");
        calcAvgScore();
        System.out.printf("사용자 평균 별점: %.1f \n", calcAvgScore());
        int i = 1;
        for (Review review : this.getReviews()) {
            System.out.println(i +". " +
                    "작성자: " + review.getWriter()
                    + " 평점: " + review.getScore()
                    + " 리뷰 내용: " + review.getComment()
                    + " 작성일: " + review.getDate().format(FORMATTER));
            i++;
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

    public float calcAvgScore() {
        if (reviews.isEmpty()) return avgScore;
        float sum = 0;
        for (Review review : reviews) {
            sum += review.getScore();
        }
        avgScore = sum / reviews.size();
        return avgScore;
    }

    public boolean isMediaSearch(String keyword) {
        return false;
    }

    public float getAvgScore() {
        return avgScore;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setAvgScore(float avgScore) {
        this.avgScore = avgScore;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }


}
