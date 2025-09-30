import domain.audio.Music;
import domain.*;
import service.MediaTask;
import service.MediaReminder;
import test.TestData;
import domain.video.Drama;
import domain.video.Movie;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("실행중인 스레드: " + Thread.currentThread().getName());
        boolean exit = true;
        MediaTask mediaTask = new MediaTask();
        TestData.loadTest(mediaTask);
        while(exit) {
            showMenu();
            Thread thread = new Thread(new MediaReminder(mediaTask));
            thread.start();
            int command = -1;
            try {
                command = sc.nextInt();
                thread.interrupt();
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해 주세요");
            } finally {
                sc.nextLine();
            }
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
        mediaTask.search(sc);
    }

    private static void deleteMedia(MediaTask mediaTask) {
        mediaTask.delete(sc);
    }

    private static void writeReview(MediaTask mediaTask) {
        mediaTask.writeReview(sc);
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
        int com = -1;
        try {
            com = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해 주세요!");
        } finally {
            sc.nextLine();
        }
        switch (com) {
            case 1:
                Media movie = new Movie();
                mediaTask.addVideo(movie.registerMedia(sc));
                break;
            case 2:
                Media drama = new Drama();
                mediaTask.addVideo(drama.registerMedia(sc));
                break;

            case 3:
                Media music = new Music();
                mediaTask.addMusic(music.registerMedia(sc));
                break;

            case 0:
                break;

            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }
    public static void viewAll(MediaTask mediaTask) {
        mediaTask.viewAll(sc);
    }


}