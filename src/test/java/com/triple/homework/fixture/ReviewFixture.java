package com.triple.homework.fixture;

import com.triple.homework.review.domain.Review;

import java.util.UUID;

public class ReviewFixture {

    public static Review review() {
        return Review.builder()
                .id(UUID.randomUUID().toString())
                .placeId(UUID.randomUUID().toString())
                .userId(UUID.randomUUID().toString())
                .content("테스트 컨텐츠")
                .isDeleted(false)
                .build();
    }

    public static Review haveNotContents() {
        return Review.builder()
                .id(UUID.randomUUID().toString())
                .placeId(UUID.randomUUID().toString())
                .userId(UUID.randomUUID().toString())
                .isDeleted(false)
                .build();
    }
}
