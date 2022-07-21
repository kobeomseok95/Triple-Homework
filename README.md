# 트리플여행자 클럽 마일리지 서비스 과제
## 실행 방법
```shell
git clone https://github.com/kobeomseok95/Triple-Homework.git triple

cd triple

./gradlew clean build

docker-compose up --build -d
```

## 구현한 API
### `POST /events`
- 이벤트를 받아 처리하는 API 입니다.
### `GET /users/{userId}/points`
- 사용자의 현재 보유 포인트를 조회하는 API 입니다.

## 개선점
- User, Review의 Aggregate은 다르다. 엔티티 내부 로직에서 수정하지 않는게 베스트
- 다른 적립 이벤트 타입에 대해 확장성을 고려해보았는지?
  - 이렇게 된다면 클래스명 부터 잘못되었음
- 동시 접근시에는 어떻게 처리할 것인가?
  - 데이터베이스 LOCK?
