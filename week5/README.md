## 5주차 과제

- Spring Doc + Swagger UI 적용
- SOLID 원칙 기반 리팩토링
- Spring AOP 기능 추가

## 4주차 과제 수정
### 진행완료
- 모든 Repository에서 id Long -> AtomicLong으로 변경
- 모든 Repository에서 map -> ConcurrentHashMap 변경
- UserRepository에서 emailMap, nicknameMap 사용해 findByEmail, findByNickname 성능 개선
- DTO 클래스 Setter 모두 삭제
- Service에서 반복되는 if (!user.getId().equals(target.getId())) AuthValidator 클래스 생성해 사용

### 진행중

- Comment, Post 양방향 관계 삭제 구현

## 5주차 과제

- Spring Doc + Swagger UI 적용
    - Spring Doc
  
    - Swagger UI
  
- SOLID 원칙 적용
    - DIP 원칙 구현

- 간단한 AOP 기능 구현
  - 간단한 로깅 기능 추가

