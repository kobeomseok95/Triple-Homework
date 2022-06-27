package com.triple.homework.review.application.service;

import com.triple.homework.common.exception.review.ReviewNotFoundException;
import com.triple.homework.fixture.ReviewFixture;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDtoBuilder;
import com.triple.homework.review.application.port.in.response.UserPointHistoryResponseDto;
import com.triple.homework.review.application.port.out.ReviewPort;
import com.triple.homework.review.domain.Review;
import com.triple.homework.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ModifyReviewEventServiceTest {

    @Mock ReviewPort reviewPort;
    @Mock CalculateReviewPointService calculateReviewPointService;
    @InjectMocks ModifyReviewEventService modifyReviewEventService;

    @DisplayName("리뷰 수정 - 실패 / 작성되지 않은 리뷰인 경우")
    @Test
    void modify_review_fail_not_written_review() throws Exception {

        // given
        ReviewEventRequestDto requestDto = ReviewEventRequestDtoBuilder.build();
        when(reviewPort.findByIdWithUserAttachedPhotos(any()))
                .thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> modifyReviewEventService.handleEvent(requestDto))
                .isInstanceOf(ReviewNotFoundException.class);
    }

    @DisplayName("리뷰 수정 - 성공")
    @Test
    void modify_review_success() throws Exception {

        // given
        Review review = ReviewFixture.review();
        User user = review.getUser();
        ReviewEventRequestDto requestDto = ReviewEventRequestDto.builder()
                .reviewId(review.getId())
                .userId(user.getId())
                .placeId(review.getPlaceId())
                .content("테스트 컨텐츠")
                .attachedPhotoIds(List.of(
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString()
                ))
                .build();
        Long beforeReviewPoints = review.getReviewPoints();
        when(reviewPort.findByIdWithUserAttachedPhotos(any()))
                .thenReturn(Optional.of(review));

        // when
        UserPointHistoryResponseDto responseDto = modifyReviewEventService.handleEvent(requestDto);

        // then
        assertAll(
                () -> verify(reviewPort).findByIdWithUserAttachedPhotos(requestDto.getReviewId()),
                () -> assertEquals(user.getId(), responseDto.getUser().getId()),
                () -> assertEquals(review.getReviewPoints() - beforeReviewPoints, responseDto.getChangedPoint())
        );
    }
}
