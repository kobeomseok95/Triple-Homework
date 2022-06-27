package com.triple.homework.review.domain;

import com.triple.homework.fixture.ReviewFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReviewTest {

    Review review;

    @BeforeEach
    void setUp() {
        review = ReviewFixture.review();
    }

    @DisplayName("리뷰 첨부파일 추가 - 성공")
    @Test
    void add_attachedPhotos_success() throws Exception {

        // given
        List<String> attachedPhotoIds = List.of(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );

        // when
        review.addAttachedPhotos(attachedPhotoIds);

        // then
        assertEquals(review.getAttachedPhotos().getAttachedPhotos().size(), attachedPhotoIds.size());
    }
}