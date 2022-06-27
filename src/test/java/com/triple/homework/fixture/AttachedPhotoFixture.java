package com.triple.homework.fixture;

import com.triple.homework.review.domain.AttachedPhoto;

import java.util.UUID;

public class AttachedPhotoFixture {

    public static AttachedPhoto attachedPhoto() {
        return AttachedPhoto.builder()
                .id(UUID.randomUUID().toString())
                .review(ReviewFixture.review())
                .build();
    }
}
