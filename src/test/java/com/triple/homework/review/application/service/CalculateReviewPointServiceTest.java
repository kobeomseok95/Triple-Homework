package com.triple.homework.review.application.service;

import com.triple.homework.fixture.AttachedPhotoFixture;
import com.triple.homework.fixture.ReviewFixture;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDtoBuilder;
import com.triple.homework.review.application.port.out.ReviewPort;
import com.triple.homework.review.domain.AttachedPhoto;
import com.triple.homework.review.domain.Review;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculateReviewPointServiceTest {

    @Mock
    ReviewPort reviewPort;
    @InjectMocks CalculateReviewPointService calculateReviewPointService;

    @DisplayName("점수 계산 테스트")
    @ParameterizedTest
    @MethodSource("reviewEventsRequestDtos")
    void calculate_point_test(ReviewEventRequestDto requestDto, Long expectedPoint) throws Exception {

        // given
        when(reviewPort.existsByPlaceId(any()))
                .thenReturn(true);

        // when, then
        assertThat(calculateReviewPointService.calculatePoint(requestDto))
                .isEqualTo(expectedPoint);
    }

    private static Stream<Arguments> reviewEventsRequestDtos() {
        return Stream.of(
                Arguments.of(ReviewEventRequestDtoBuilder.buildNoContentAndPhotos(), 0L),
                Arguments.of(ReviewEventRequestDtoBuilder.buildHaveText(), 1L),
                Arguments.of(ReviewEventRequestDtoBuilder.buildHavePhoto(), 1L),
                Arguments.of(ReviewEventRequestDtoBuilder.build(), 2L)
        );
    }

    @DisplayName("점수 계산 테스트 - 장소에 대한 첫 리뷰 작성")
    @ParameterizedTest
    @MethodSource("reviewEventsRequestDtos")
    void calculate_point_test_first_review(ReviewEventRequestDto requestDto, Long expectedPoint) throws Exception {

        // given
        when(reviewPort.existsByPlaceId(any()))
                .thenReturn(false);

        // when, then
        assertThat(calculateReviewPointService.calculatePoint(requestDto))
                .isEqualTo(expectedPoint + 1L);
    }

    @DisplayName("점수 계산 테스트 - 수정한 리뷰가 내용이 없고, 사진이 없다면 -2점 반환")
    @Test
    void calculate_point_success_different_contents_and_photos_decrease_points() throws Exception {

        // given
        Review review = ReviewFixture.review();
        List<AttachedPhoto> attachedPhotos = List.of(AttachedPhotoFixture.attachedPhoto());
        ReviewEventRequestDto requestDto = ReviewEventRequestDtoBuilder.buildNoContentAndPhotos();

        // when, then
        assertThat(calculateReviewPointService.calculatePoint(review, attachedPhotos, requestDto))
                .isEqualTo(-2L);
    }

    @DisplayName("점수 계산 테스트 - 수정한 리뷰가 내용이 있고, 사진이 있다면 2점 반환")
    @Test
    void calculate_point_success_different_contents_and_photos_increase_points() throws Exception {

        // given
        Review review = ReviewFixture.haveNotContents();
        List<AttachedPhoto> attachedPhotos = Collections.emptyList();
        ReviewEventRequestDto requestDto = ReviewEventRequestDtoBuilder.build();

        // when, then
        assertThat(calculateReviewPointService.calculatePoint(review, attachedPhotos, requestDto))
                .isEqualTo(2L);
    }
}
