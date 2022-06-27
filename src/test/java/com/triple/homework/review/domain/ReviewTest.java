package com.triple.homework.review.domain;

import com.triple.homework.common.exception.review.PlaceIdIsDifferentException;
import com.triple.homework.fixture.ReviewFixture;
import com.triple.homework.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReviewTest {

    @DisplayName("리뷰 첨부파일 추가 - 성공")
    @Test
    void add_attachedPhotos_success() throws Exception {

        // given
        Review review = ReviewFixture.review();
        List<String> attachedPhotoIds = List.of(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );

        // when
        review.addAttachedPhotos(attachedPhotoIds);

        // then
        assertEquals(review.getAttachedPhotos().getAttachedPhotos().size(), attachedPhotoIds.size());
    }

    @DisplayName("리뷰 변경 - 실패 / 남겼던 리뷰의 장소와 다를 경우")
    @Test
    void modify_review_fail_different_place_id() throws Exception {

        // given
        String wantedModifyPlaceId = UUID.randomUUID().toString();
        String content = "test";
        Review review = ReviewFixture.review();

        // when, then
        assertThatThrownBy(() -> review.modify(content, wantedModifyPlaceId, List.of()))
                .isInstanceOf(PlaceIdIsDifferentException.class);
    }

    @DisplayName("리뷰 변경 - 성공")
    @ParameterizedTest
    @MethodSource("testModifyReviews")
    void modify_review_and_change_user_point_success(ModifyReviewRequestFields requestFields,
                                                     Long expectChangedReviewPoint) throws Exception {

        // given
        Review review = ReviewFixture.review();

        // when
        review.modify(requestFields.getContent(),
                review.getPlaceId(),
                requestFields.getAttachedPhotoIds());

        // then
        assertAll(
                () -> assertEquals(review.getContent(), requestFields.getContent()),
                () -> assertEquals(review.getAttachedPhotos().getAttachedPhotos().size(), requestFields.getAttachedPhotoIds().size()),
                () -> assertEquals(review.getReviewPoints(), expectChangedReviewPoint)
        );
    }

    private static Stream<Arguments> testModifyReviews() {
        return Stream.of(
                Arguments.of(new ModifyReviewRequestFields("", Collections.emptyList()), 1L),
                Arguments.of(new ModifyReviewRequestFields("테스트", Collections.emptyList()), 2L),
                Arguments.of(new ModifyReviewRequestFields("", List.of("8d077afa-314d-464a-b29c-5755e5b598f7")), 2L),
                Arguments.of(new ModifyReviewRequestFields("테스트", List.of("8d077afa-314d-464a-b29c-5755e5b598f7")), 3L)
        );
    }

    static class ModifyReviewRequestFields {
        private String content;
        private List<String> attachedPhotoIds;

        public ModifyReviewRequestFields(String content, List<String> attachedPhotoIds) {
            this.content = content;
            this.attachedPhotoIds = attachedPhotoIds;
        }

        public String getContent() {
            return content;
        }

        public List<String> getAttachedPhotoIds() {
            return attachedPhotoIds;
        }
    }

    @DisplayName("리뷰 삭제 시 유저 포인트 차감 - 성공")
    @Test
    void decrease_user_point_success() throws Exception {

        // given
        Review review = ReviewFixture.review();
        User user = review.getUser();
        Long beforeUserPoints = user.getUserPoints();

        // when
        review.decreaseUsersPointAndReturnReviewPoint();

        // then
        assertAll(
                () -> assertEquals(user.getUserPoints(), beforeUserPoints - review.getReviewPoints())
        );
    }
}
