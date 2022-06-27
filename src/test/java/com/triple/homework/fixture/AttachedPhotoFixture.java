package com.triple.homework.fixture;

import com.triple.homework.review.domain.AttachedPhoto;
import com.triple.homework.review.domain.Review;

import java.util.UUID;

public class AttachedPhotoFixture {

    public static AttachedPhoto attachedPhoto() {
        return AttachedPhoto.builder()
                .id(UUID.randomUUID().toString())
                .review(ReviewFixture.review())
                .build();
    }

    public static AttachedPhoto attachedPhoto(Review review) {
        return AttachedPhoto.builder()
                .id(UUID.randomUUID().toString())
                .review(review)
                .build();
    }
}
