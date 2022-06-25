package com.triple.homework.review.application.service;

import com.triple.homework.common.exception.review.WrittenReviewByUserAndPlaceException;
import com.triple.homework.fixture.UserFixture;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDtoBuilder;
import com.triple.homework.review.application.port.out.ReviewRepository;
import com.triple.homework.review.application.port.out.ReviewToUserRepository;
import com.triple.homework.review.domain.Review;
import com.triple.homework.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddReviewEventServiceTest {

    @Mock ReviewRepository reviewRepository;
    @Mock CalculateReviewPointService calculateReviewPointService;
    @Mock ReviewToUserRepository userRepository;
    @InjectMocks AddReviewEventService addReviewEventService;

    @DisplayName("ADD 이벤트 실패 - 유저가 이미 장소에 대한 리뷰가 있는 경우")
    @Test
    void add_review_fail_user_has_written_review() throws Exception {

        // given
        ReviewEventRequestDto requestDto = mock(ReviewEventRequestDto.class);
        when(reviewRepository.existsByUserIdAndPlaceId(any(), any()))
                .thenReturn(true);

        // when, then
        assertThatThrownBy(() -> addReviewEventService.handleEvent(requestDto))
                .isInstanceOf(WrittenReviewByUserAndPlaceException.class);
    }

    @DisplayName("ADD 이벤트 성공 - 유저가 저장되어 있지 않은 경우")
    @Test
    void add_review_success_not_exists_user() throws Exception {

        // given
        ReviewEventRequestDto requestDto = ReviewEventRequestDtoBuilder.build();
        when(reviewRepository.existsByUserIdAndPlaceId(any(), any()))
                .thenReturn(false);
        when(calculateReviewPointService.calculatePoint(requestDto))
                .thenReturn(3L);
        when(userRepository.findById(any()))
                .thenReturn(Optional.empty());

        // when
        addReviewEventService.handleEvent(requestDto);

        // then
        assertAll(
                () -> verify(reviewRepository).existsByUserIdAndPlaceId(requestDto.getUserId(), requestDto.getPlaceId()),
                () -> verify(calculateReviewPointService).calculatePoint(requestDto),
                () -> verify(userRepository).findById(requestDto.getUserId()),
                () -> verify(userRepository).save(any(User.class)),
                () -> verify(reviewRepository).save(any(Review.class))
        );
    }

    @DisplayName("ADD 이벤트 성공 - 유저 정보가 저장된 경우")
    @Test
    void add_review_success_exists_user() throws Exception {

        // given
        ReviewEventRequestDto requestDto = ReviewEventRequestDtoBuilder.build();
        when(reviewRepository.existsByUserIdAndPlaceId(any(), any()))
                .thenReturn(false);
        when(calculateReviewPointService.calculatePoint(requestDto))
                .thenReturn(3L);
        when(userRepository.findById(any()))
                .thenReturn(Optional.of(UserFixture.user()));

        // when
        addReviewEventService.handleEvent(requestDto);

        // then
        assertAll(
                () -> verify(reviewRepository).existsByUserIdAndPlaceId(requestDto.getUserId(), requestDto.getPlaceId()),
                () -> verify(calculateReviewPointService).calculatePoint(requestDto),
                () -> verify(userRepository).findById(requestDto.getUserId()),
                () -> verify(userRepository, times(0)).save(any(User.class)),
                () -> verify(reviewRepository).save(any(Review.class))
        );
    }
}
