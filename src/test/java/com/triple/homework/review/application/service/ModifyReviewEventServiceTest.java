package com.triple.homework.review.application.service;

import com.triple.homework.common.exception.review.NotWrittenReviewException;
import com.triple.homework.common.exception.user.UserNotFoundException;
import com.triple.homework.fixture.AttachedPhotoFixture;
import com.triple.homework.fixture.ReviewFixture;
import com.triple.homework.fixture.UserFixture;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDtoBuilder;
import com.triple.homework.review.application.port.out.AttachedPhotoPort;
import com.triple.homework.review.application.port.out.ReviewPort;
import com.triple.homework.review.domain.AttachedPhoto;
import com.triple.homework.review.domain.Review;
import com.triple.homework.user.application.port.out.UserPort;
import com.triple.homework.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ModifyReviewEventServiceTest {

    @Mock ReviewPort reviewPort;
    @Mock UserPort userPort;
    @Mock AttachedPhotoPort attachedPhotoPort;
    @Mock CalculateReviewPointService calculateReviewPointService;
    @InjectMocks ModifyReviewEventService modifyReviewEventService;

    @DisplayName("리뷰 수정 - 실패 / 작성되지 않은 리뷰인 경우")
    @Test
    void modify_review_fail_not_written_review() throws Exception {

        // given
        ReviewEventRequestDto requestDto = ReviewEventRequestDtoBuilder.build();
        when(reviewPort.findById(any()))
                .thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> modifyReviewEventService.handleEvent(requestDto))
                .isInstanceOf(NotWrittenReviewException.class);
    }

    @DisplayName("리뷰 수정 - 실패 / 존재하지 않는 유저인 경우")
    @Test
    void modify_review_fail_not_found_user() throws Exception {

        // given
        ReviewEventRequestDto requestDto = ReviewEventRequestDtoBuilder.build();
        Review review = ReviewFixture.review();
        when(reviewPort.findById(any()))
                .thenReturn(Optional.of(review));
        when(userPort.findById(any()))
                .thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> modifyReviewEventService.handleEvent(requestDto))
                .isInstanceOf(UserNotFoundException.class);
    }

    @DisplayName("리뷰 수정 - 성공")
    @Test
    void modify_review_success() throws Exception {

        // given
        ReviewEventRequestDto requestDto = ReviewEventRequestDtoBuilder.buildHaveText();
        Review review = ReviewFixture.review();
        when(reviewPort.findById(any()))
                .thenReturn(Optional.of(review));
        User user = UserFixture.user();
        Long beforePointScore = user.getPointScore();
        when(userPort.findById(any()))
                .thenReturn(Optional.of(user));
        List<AttachedPhoto> attachedPhotos = List.of(AttachedPhotoFixture.attachedPhoto());
        when(attachedPhotoPort.findByReviewId(any()))
                .thenReturn(attachedPhotos);
        long afterPointScore = 1L;
        when(calculateReviewPointService.calculatePoint(review, attachedPhotos, requestDto))
                .thenReturn(afterPointScore);

        // when
        modifyReviewEventService.handleEvent(requestDto);

        // then
        assertAll(
                () -> verify(reviewPort).findById(requestDto.getReviewId()),
                () -> verify(userPort).findById(requestDto.getUserId()),
                () -> verify(attachedPhotoPort).findByReviewId(requestDto.getReviewId()),
                () -> verify(attachedPhotoPort).saveAll(any(List.class)),
                () -> verify(calculateReviewPointService).calculatePoint(review, attachedPhotos, requestDto),
                () -> assertThat(user.getPointScore()).isEqualTo(beforePointScore + afterPointScore),
                () -> assertThat(review.getContent()).isEqualTo(requestDto.getContent()),
                () -> assertThat(attachedPhotos).allMatch(AttachedPhoto::isDeleted)
        );
    }
}
