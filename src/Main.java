import domain.*;
import test.TestData;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        MediaTask mediaTask = new MediaTask();
        TestData.loadTest(mediaTask);
        while(exit) {
            showMenu();
            int command = sc.nextInt();
            switch (command) {
                case 1: register(mediaTask); break;
                case 2: viewAll(mediaTask); break;
                case 3: writeReview(mediaTask); break;
                case 4: searchMedia(mediaTask); break;
                case 5: deleteMedia(mediaTask); break;
                case 0:
                    System.out.println("미디어 관리 프로그램을 종료합니다.");
                    exit = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 올바른 번호를 입력해주세요."); break;
            }
        }
    }

    private static void searchMedia(MediaTask mediaTask) {
        mediaTask.search();
    }

    private static void deleteMedia(MediaTask mediaTask) {
        mediaTask.delete();
    }

    private static void writeReview(MediaTask mediaTask) {
        mediaTask.writeReview();
    }

    public static void showMenu() {
        System.out.println("1. 작품 등록");
        System.out.println("2. 작품 조회");
        System.out.println("3. 리뷰 작성");
        System.out.println("4. 작품 검색");
        System.out.println("5. 작품 삭제");
        System.out.println("0. 종료");
        System.out.print(">>>> ");
    }

    public static void register(MediaTask mediaTask) {
        System.out.println("등록할 작품 타입을 선택하세요");
        System.out.println("1. 영화");
        System.out.println("2. 드라마");
        System.out.println("3. 대중음악");
        System.out.println("0. 돌아가기");
        System.out.print(">>>> ");
        Scanner sc = new Scanner(System.in);
        int com = sc.nextInt();
        switch (com) {
            case 1:
                Media movie = new Movie();
                mediaTask.addVideo(movie.registerMedia());
                break;
            case 2:
                Media drama = new Drama();
                mediaTask.addVideo(drama.registerMedia());
                break;

            case 3:
                Media music = new Music();
                mediaTask.addMusic(music.registerMedia());
                break;

            case 0:
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }
    public static void viewAll(MediaTask mediaTask) {
        mediaTask.viewAll();
    }


}