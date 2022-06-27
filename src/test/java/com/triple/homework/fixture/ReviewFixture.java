package com.triple.homework.fixture;

import com.triple.homework.review.domain.Review;

import java.util.UUID;

public class ReviewFixture {

    public static Review review() {
        return Review.builder()
                .id(UUID.randomUUID().toString())
                .user(UserFixture.user())
                .placeId(UUID.randomUUID().toString())
                .content("테스트 컨텐츠")
                .build();
    }

    public static Review haveNotContents() {
        return Review.builder()
                .id(UUID.randomUUID().toString())
                .user(UserFixture.user())
                .placeId(UUID.randomUUID().toString())
                .build();
    }
}
