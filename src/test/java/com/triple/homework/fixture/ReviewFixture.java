package com.triple.homework.fixture;

import com.triple.homework.review.domain.AttachedPhotos;
import com.triple.homework.review.domain.Review;

import java.util.ArrayList;
import java.util.List;
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

    public static Review withAttachedPhotos() {
        return Review.builder()
                .id(UUID.randomUUID().toString())
                .user(UserFixture.user())
                .placeId(UUID.randomUUID().toString())
                .content("테스트 컨텐츠")
                .attachedPhotos(AttachedPhotos.builder()
                        .attachedPhotos(new ArrayList<>(List.of(
                                AttachedPhotoFixture.attachedPhoto(),
                                AttachedPhotoFixture.attachedPhoto())))
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
