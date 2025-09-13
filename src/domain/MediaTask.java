package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MediaTask {
    public List<Media> mediaList = new ArrayList<>();
    public List<Media> videoList = new ArrayList<>();
    public List<Media> audioList = new ArrayList<>();

    public void addVideo(Media video) {
        mediaList.add(video);
        videoList.add(video);
    }

    public void addMusic(Media audio) {
        mediaList.add(audio);
        audioList.add(audio);
    }

    public void viewAll() {
        System.out.println("==== 미디어 목록 ====");
        System.out.println("상세히 보고 싶은 작품 번호를 입력해주세요");
        int i = 1;
        for (Media m : mediaList) {
            System.out.print(i + ". ");
            m.viewList();
            i++;
        }
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        mediaList.get(choice - 1).viewDetail();
        System.out.println("뒤로 가려면 아무 숫자나 입력하세요");
        sc.nextInt();
    }

    public void writeReview() {
        System.out.println("==== 작품 목록 ====");
        System.out.println("리뷰를 작성하고 싶은 작품을 선택하세요");
        int i = 1;
        for (Media m : mediaList) {
            System.out.print(i + ". ");
            m.viewList();
            i++;
        }
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        mediaList.get(choice - 1).addReview();
    }

    public void search() {
        String keyword;
        int count = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("검색할 함목을 선택하세요");
        System.out.println("1. 비디오");
        System.out.println("2. 오디오");
        System.out.println("0. 뒤로가기");
        System.out.print(">>>> ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                System.out.println("검색할 제목, 배우를 입력하세요(단어)");
                keyword = sc.nextLine();
                for (int i = 0; i < videoList.size(); i++) {
                    if(videoList.get(i).search(keyword)) {
                        System.out.print(i + ". ");
                        videoList.get(i).viewList();
                        count++;
                    }
                }
                if (count == 0) break;
                System.out.println("확인할 작품을 선택하세요.");
                choice = sc.nextInt();
                videoList.get(choice).viewDetail();
                System.out.println("뒤로 가려면 아무 숫자나 입력하세요");
                sc.nextInt();
                break;
            case 2:
                count = 0;
                System.out.println("검색할 제목, 아티스트를 입력하세요(단어)");
                keyword = sc.nextLine();
                for (int i = 0; i < audioList.size(); i++) {
                    if(audioList.get(i).search(keyword)) {
                        System.out.print(i + ". ");
                        audioList.get(i).viewList();
                        count++;
                    }
                }
                if (count == 0) break;
                System.out.println("확인할 작품을 선택하세요.");
                choice = sc.nextInt();
                audioList.get(choice).viewDetail();
                System.out.println("뒤로 가려면 아무 숫자나 입력하세요");
                sc.nextInt();
                break;
            case 0: break;
        }
    }

    public void delete() {
        System.out.println("==== 작품 목록 ====");
        System.out.println("삭제하고 싶은 작품을 선택하세요");
        int i = 1;
        for (Media m : mediaList) {
            System.out.print(i + ". ");
            m.viewList();
            i++;
        }
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        Media remove = mediaList.remove(choice - 1);
        if (videoList.contains(remove)) {
            videoList.remove(remove);
        } else {
            audioList.remove(remove);
        }
    }
}
