package com.triple.homework.review.adapter.out;

import com.triple.homework.common.exception.review.ReviewNotFoundException;
import com.triple.homework.fixture.AttachedPhotoFixture;
import com.triple.homework.fixture.ReviewFixture;
import com.triple.homework.review.domain.AttachedPhoto;
import com.triple.homework.review.domain.Review;
import com.triple.homework.support.JpaRepositoryTest;
import com.triple.homework.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Import({ReviewRepository.class})
class ReviewRepositoryTest extends JpaRepositoryTest {

    @Autowired ReviewRepository reviewRepository;

    @DisplayName("유저 ID, 장소 ID로 리뷰 찾기 - 성공")
    @Test
    void exists_by_userId_and_placeId_exist() throws Exception {

        // given
        Review review = ReviewFixture.review();
        entityManager.persist(review.getUser());
        entityManager.persist(review);
        flushAndClear();

        // when, then
        assertThat(reviewRepository.existsByUserIdAndPlaceId(review.getUser().getId(), review.getPlaceId()))
                .isTrue();
    }

    @DisplayName("유저ID, 장소 ID로 리뷰 찾기 - 실패")
    @Test
    void exists_by_userId_and_placeId_not_exist() throws Exception {

        // given
        Review review = ReviewFixture.review();

        // when, then
        assertThat(reviewRepository.existsByUserIdAndPlaceId(review.getUser().getId(), review.getPlaceId()))
                .isFalse();
    }

    @DisplayName("장소 ID로 리뷰 찾기 - 성공")
    @Test
    void exists_by_placeId_exist() throws Exception {

        // given
        Review review = ReviewFixture.review();
        entityManager.persist(review.getUser());
        entityManager.persist(review);
        flushAndClear();

        // when, then
        assertThat(reviewRepository.existsByPlaceId(review.getPlaceId()))
                .isTrue();
    }

    @DisplayName("장소 ID로 리뷰 찾기 - 실패")
    @Test
    void exists_by_placeId_not_exist() throws Exception {

        // given
        Review review = ReviewFixture.review();

        // when, then
        assertThat(reviewRepository.existsByPlaceId(review.getPlaceId()))
                .isFalse();
    }

    @DisplayName("리뷰 ID로 user, attachedphotos fetch join 조회 - 성공 / 사진이 있을 경우")
    @Test
    void findByIdWithUserAttachedPhotos_success_exist_attachedPhotos() throws Exception {

        // given
        Review review = ReviewFixture.review();
        entityManager.persist(review.getUser());
        entityManager.persist(review);
        AttachedPhoto attachedPhoto1 = AttachedPhotoFixture.attachedPhoto(review);
        AttachedPhoto attachedPhoto2 = AttachedPhotoFixture.attachedPhoto(review);
        entityManager.persist(attachedPhoto1);
        entityManager.persist(attachedPhoto2);
        flushAndClear();

        // when
        Review findReview = reviewRepository.findByIdWithUserAttachedPhotos(review.getId()).get();

        // then
        assertAll(
                () -> assertEquals(findReview.getUser().getId(), review.getUser().getId()),
                () -> assertEquals(findReview.getAttachedPhotos().getAttachedPhotos().size(), 2)
        );
    }

    @DisplayName("리뷰 ID로 user, attachedphotos fetch join 조회 - 성공 / 사진이 없을 경우")
    @Test
    void findByIdWithUserAttachedPhotos_success_not_exist_attachedPhotos() throws Exception {

        // given
        Review review = ReviewFixture.review();
        entityManager.persist(review.getUser());
        entityManager.persist(review);
        flushAndClear();

        // when
        Review findReview = reviewRepository.findByIdWithUserAttachedPhotos(review.getId()).get();

        // then
        assertAll(
                () -> assertEquals(findReview.getUser().getId(), review.getUser().getId()),
                () -> assertEquals(findReview.getAttachedPhotos().getAttachedPhotos().size(), 0)
        );
    }

    @DisplayName("리뷰 삭제 - 성공 / 저장된 사진도 삭제되어야 한다.")
    @Test
    void delete_review_success() throws Exception {

        // given
        Review review = ReviewFixture.review();
        User user = review.getUser();
        AttachedPhoto attachedPhoto = AttachedPhotoFixture.attachedPhoto(review);
        entityManager.persist(user);
        entityManager.persist(review);
        entityManager.persist(attachedPhoto);
        flushAndClear();

        // when
        Review findReview = reviewRepository.findByIdWithUserAttachedPhotos(review.getId()).get();
        reviewRepository.delete(findReview);
        flushAndClear();

        // then
        assertThatThrownBy(() -> reviewRepository.findByIdWithUserAttachedPhotos(review.getId())
                .orElseThrow(ReviewNotFoundException::new))
                .isInstanceOf(ReviewNotFoundException.class);
    }
}
