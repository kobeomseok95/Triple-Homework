package com.triple.homework.review.application.service;

import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDtoBuilder;
import com.triple.homework.review.application.port.out.ReviewPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
