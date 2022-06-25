package com.triple.homework.review.adapter.out;

import com.triple.homework.fixture.ReviewFixture;
import com.triple.homework.review.domain.Review;
import com.triple.homework.support.JpaRepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@Import({ReviewRepository.class})
class ReviewRepositoryTest extends JpaRepositoryTest {

    @Autowired ReviewRepository reviewRepository;

    @DisplayName("유저 ID, 장소 ID로 리뷰 찾기 - 성공")
    @Test
    void exists_by_userId_and_placeId_exist() throws Exception {

        // given
        Review review = ReviewFixture.review();
        entityManager.persist(review);
        flushAndClear();

        // when, then
        assertThat(reviewRepository.existsByUserIdAndPlaceId(review.getUserId(), review.getPlaceId()))
                .isTrue();
    }

    @DisplayName("유저ID, 장소 ID로 리뷰 찾기 - 실패")
    @Test
    void exists_by_userId_and_placeId_not_exist() throws Exception {

        // given
        Review review = ReviewFixture.review();

        // when, then
        assertThat(reviewRepository.existsByUserIdAndPlaceId(review.getUserId(), review.getPlaceId()))
                .isFalse();
    }

    @DisplayName("장소 ID로 리뷰 찾기 - 성공")
    @Test
    void exists_by_placeId_exist() throws Exception {

        // given
        Review review = ReviewFixture.review();
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
}
