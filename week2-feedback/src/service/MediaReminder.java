package service;

import domain.Media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MediaReminder implements Runnable{

    private MediaTask mediaTask;
    public List<Media> list = new ArrayList<>();
    public MediaReminder() {
    }

    public MediaReminder(MediaTask mediaTask) {
        this.mediaTask = mediaTask;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println("실행중인 스레드: " + Thread.currentThread().getName());
            System.out.println();
            System.out.println("====가장 좋은 평점을 받은 작품들 입니다!====");
            System.out.println("====리뷰를 작성하기 전에 참고하세요====");
            reviewReminding();
            System.out.println("메뉴에서 번호를 다시 선택해주세요");
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void reviewReminding() {
        list.addAll(mediaTask.mediaList);
        Collections.sort(list, new Comparator<Media>() {
            @Override
            public int compare(Media o1, Media o2) {

                int compare = Float.compare(o2.getAvgScore(), o1.getAvgScore());
                if (compare == 0) {
                    return o2.getReviews().size() - o1.getReviews().size();
                }
                return compare;
            }
        });
        if(list.size() < 3) {
            for (Media media : list) {
                media.viewList();
            }
        } else {
            for (int i = 0; i < 3; i++) {
                list.get(i).viewList();
            }
        }
    }
}
