package com.triple.homework.fixture;

import com.triple.homework.review.domain.AttachedPhotos;
import com.triple.homework.review.domain.Review;

import java.util.ArrayList;
import java.util.UUID;

public class ReviewFixture {

    public static Review review() {
        return Review.builder()
                .id(UUID.randomUUID().toString())
                .user(UserFixture.user())
                .placeId(UUID.randomUUID().toString())
                .content("테스트 컨텐츠")
                .reviewPoints(2L)
                .attachedPhotos(AttachedPhotos.builder()
                        .attachedPhotos(new ArrayList<>())
                        .build())
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
