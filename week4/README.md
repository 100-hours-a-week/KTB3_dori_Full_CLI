## 4주차 과제

REST API 프로그램 구현

## 프로그램 설명

- REST API 설계서를 통해 커뮤니티 기능 개발
- JPA, DB 사용없이 Repository 기능 구현
- User, Post, Comment를 기반으로 기능 구현

## 3주차 과제 수정

- /signup, /login RESTful 원칙에서 행위를 나타냄 -> 로그인 /auth , 회원가입 /users 로 수정
- 게시글 삭제 시 상태코드 기존 200 Ok -> 204 No Content를 사용해 바디에 내용없이 전달하도록 수정
- 비밀번호 틀릴시에 409 Conflict 오류가 발생하도록 설계 -> 400 Bad Request가 유효하지 않은 것을 의미하므로 수정
- 게시글 상세 조회시 댓글도 같이 나오도록 구현 -> 댓글을 따로 응답하도록 수정

## 4주차 과제
