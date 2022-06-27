package com.triple.homework.review.application.service;

import com.triple.homework.common.exception.review.ReviewNotFoundException;
import com.triple.homework.fixture.ReviewFixture;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDtoBuilder;
import com.triple.homework.review.application.port.in.response.UserPointHistoryResponseDto;
import com.triple.homework.review.application.port.out.ReviewPort;
import com.triple.homework.review.domain.Review;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteReviewEventServiceTest {

    @Mock ReviewPort reviewPort;
    @InjectMocks DeleteReviewEventService deleteReviewEventService;

    @DisplayName("리뷰 삭제 - 실패 / 직상되지 않은 리뷰인 경우")
    @Test
    void delete_review_fail_not_found_review() throws Exception {

        // given
        ReviewEventRequestDto requestDto = ReviewEventRequestDtoBuilder.build();
        when(reviewPort.findByIdWithUserAttachedPhotos(any()))
                .thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> deleteReviewEventService.handleEvent(requestDto))
                .isInstanceOf(ReviewNotFoundException.class);
    }

    @DisplayName("리뷰 삭제 - 성공")
    @Test
    void delete_review_success() throws Exception {

        // given
        ReviewEventRequestDto requestDto = ReviewEventRequestDtoBuilder.build();
        Review review = ReviewFixture.review();
        when(reviewPort.findByIdWithUserAttachedPhotos(any()))
                .thenReturn(Optional.of(review));

        // when
        UserPointHistoryResponseDto responseDto = deleteReviewEventService.handleEvent(requestDto);

        // then
        assertAll(
                () -> verify(reviewPort).findByIdWithUserAttachedPhotos(requestDto.getReviewId()),
                () -> verify(reviewPort).delete(review),
                () -> assertEquals(review.getUser().getId(), responseDto.getUser().getId()),
                () -> assertEquals(-review.getReviewPoints(), responseDto.getChangedPoint())
        );
    }
}
