## 2주차 과제

자바 CLI 기반 프로그램에 스레드 추가

## 프로그램 설명

- CLI 기반 간단한 작품 리뷰 관리 프로그램
    - 사용자는 작품을 등록하고 작품마다 리뷰를 등록할 수 있음
- 작품들은 Media를 조부모를 기본적으로 상속받고 타입에 따라 다른 2차 상속 구조를 가짐
- 각 작업은 MediaTask를 통해 작업이 수행됨

### 1주차 과제 수정

1. 폴더 네이밍 규칙이 섞여있는 것을 계층형 구조로 수정했습니다.
2. Scanner를 메인 클래스에서 static으로 선언하고 메서드에 주입하는 형식으로 수정했습니다.
3. 메뉴를 선택하는 과정에서 숫자를 입력하지 않으면 생기는 예외를 try-catch로 예외를 처리했습니다.
4. Audio와 Movie에서 변수명이 length로 동일하지만 타입이 달라서 생기는 혼란을 없애기 위해 각각 musicLength, runningTime으로 수정했습니다.
5. 날짜 포맷 문자열이 중복되는 것을 조부모 클래스에 static 변수로 FORMATTER로 선언해 중복을 제거했습니다.
6. 평점 변수인 avgScore, 계산하는 메서드인 avgCalculate() 메서드 Media(조부모 클래스)로 이동

### 2주차 과제

1. 멀티 스레드를 사용하기 위해 새로운 클래스인 MediaReminder 클래스를 생성
    - 메인 메뉴에서 5초간 입력이 없을 경우 평균 평점이 높은 미디어들을 3개 출력해주는 기능을 가진 클래스입니다.
    - Thread를 하나 더 사용하기 위해서 Runnable 인터페이스를 상속받았습니다.
    - 평점이 높은 작품으로 정렬하기 위해서 평점을 내림차순으로 정렬했습니다
        - 평점이 같을 경우 리뷰 개수가 많은 순으로 정렬됩니다.
    
    ```java
    
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
    
    ```
    
2. Runnable을 구현한 클래스를 Main에서 새로운 스레드를 생성해 구현했습니다.
    
    ```java
    Thread thread = new Thread(new MediaReminder(mediaTask));
    thread.start();
    ```
    

[화면 기록 2025-09-21 오전 12.14.19.mov](attachment:b1cda68f-1848-4327-b245-3371ced06ef1:화면_기록_2025-09-21_오전_12.14.19.mov)
