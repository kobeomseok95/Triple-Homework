package com.triple.homework.review.application.service;

import com.triple.homework.common.exception.review.ReviewNotFoundException;
import com.triple.homework.history.advisor.SavePointHistory;
import com.triple.homework.review.application.port.in.ReviewEventHandleUseCase;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;
import com.triple.homework.review.application.port.in.response.UserPointHistoryResponseDto;
import com.triple.homework.review.application.port.out.ReviewPort;
import com.triple.homework.review.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
class ModifyReviewEventService implements ReviewEventHandleUseCase {

    private final ReviewPort reviewPort;
    private final CalculateReviewPointService calculateReviewPointService;

    @Override
    public String getCode() {
        return "MOD";
    }

    @Override
    @SavePointHistory
    public UserPointHistoryResponseDto handleEvent(ReviewEventRequestDto reviewEventRequestDto) {
        Review review = reviewPort.findByIdWithUserAttachedPhotos(reviewEventRequestDto.getReviewId())
                .orElseThrow(ReviewNotFoundException::new);
        Long beforePoint = review.getReviewPoints();
        Long calculatedPoint = calculateReviewPointService.calculatePoint(reviewEventRequestDto);
        review.modify(calculatedPoint,
                reviewEventRequestDto.getContent(),
                reviewEventRequestDto.getPlaceId(),
                reviewEventRequestDto.getAttachedPhotoIds());
        return UserPointHistoryResponseDto.of(review.getUser(), beforePoint - calculatedPoint);
    }
}
