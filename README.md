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