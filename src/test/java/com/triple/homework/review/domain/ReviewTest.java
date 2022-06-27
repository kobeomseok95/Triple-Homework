package com.triple.homework.review.domain;

import com.triple.homework.fixture.ReviewFixture;
import com.triple.homework.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

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

    @DisplayName("리뷰 변경 - 성공")
    @ParameterizedTest
    @MethodSource("testReviews")
    void modify_review_and_change_user_point_success(Review review,
                                                     User user,
                                                     Long calculatedPoint) throws Exception {

        // given
        String changeContent = "변경";
        String changePlaceId = UUID.randomUUID().toString();
        List<String> changeAttachedPhotoIds = List.of(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString());
        Long beforeUserPoints = user.getUserPoints();
        Long beforeReviewPoints = review.getReviewPoints();

        // when
        Long changedUserPoints = review.modifyReviewAndReturnChangeUserPoints(calculatedPoint,
                changeContent,
                changePlaceId,
                changeAttachedPhotoIds);

        // then
        assertAll(
                () -> assertEquals(review.getReviewPoints(), calculatedPoint),
                () -> assertEquals(user.getUserPoints(), beforeUserPoints + (calculatedPoint - beforeReviewPoints)),
                () -> assertEquals(review.getReviewPoints(), calculatedPoint),
                () -> assertEquals(review.getContent(), changeContent),
                () -> assertEquals(review.getPlaceId(), changePlaceId),
                () -> assertEquals(review.getAttachedPhotos().getAttachedPhotos().size(), changeAttachedPhotoIds.size())
        );
    }

    private static Stream<Arguments> testReviews() {
        User haveThreePointsUser = User.builder()
                .id(UUID.randomUUID().toString())
                .userPoints(3L)
                .build();

        Review threePointsReview = Review.builder().user(haveThreePointsUser).reviewPoints(3L).attachedPhotos(AttachedPhotos.builder().build()).build();
        Review twoPointsReview = Review.builder().user(haveThreePointsUser).attachedPhotos(AttachedPhotos.builder().build()).reviewPoints(2L).build();
        Review onePointReview = Review.builder().user(haveThreePointsUser).attachedPhotos(AttachedPhotos.builder().build()).reviewPoints(1L).build();
        Review zeroPointsReview = Review.builder().user(haveThreePointsUser).attachedPhotos(AttachedPhotos.builder().build()).reviewPoints(0L).build();
        return Stream.of(
                Arguments.of(threePointsReview, haveThreePointsUser, 3L),
                Arguments.of(twoPointsReview, haveThreePointsUser, 2L),
                Arguments.of(onePointReview, haveThreePointsUser, 1L),
                Arguments.of(zeroPointsReview, haveThreePointsUser, 0L)
        );
    }

    @DisplayName("리뷰 삭제 시 유저 포인트 차감 - 성공")
    @Test
    void decrease_user_point_success() throws Exception {

        // given
        Review review = ReviewFixture.review();
        User user = review.getUser();
        Long beforeUserPoints = user.getUserPoints();

        // when
        Long reviewPoint = review.decreaseUsersPointAndReturnReviewPoint();

        // then
        assertAll(
                () -> assertEquals(review.getReviewPoints(), reviewPoint),
                () -> assertEquals(user.getUserPoints(), beforeUserPoints - reviewPoint)
        );
    }
}
