package com.triple.homework.review.application.service;

import com.triple.homework.common.exception.review.ReviewNotFoundException;
import com.triple.homework.history.advisor.SavePointHistory;
import com.triple.homework.review.application.port.in.ReviewEventHandleUseCase;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;
import com.triple.homework.review.application.port.in.response.UserPointHistoryResponseDto;
import com.triple.homework.review.application.port.out.ReviewPort;
import com.triple.homework.review.domain.Review;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class ModifyReviewEventService implements ReviewEventHandleUseCase {

    private final ReviewPort reviewPort;
    // FIXME: 2022/07/11 kobeomseok95 삭제
    private final CalculateReviewPointService calculateReviewPointService;

    public ModifyReviewEventService(ReviewPort reviewPort, CalculateReviewPointService calculateReviewPointService) {
        this.reviewPort = reviewPort;
        this.calculateReviewPointService = calculateReviewPointService;
    }

    @Override
    public String getCode() {
        return "MOD";
    }

    @Override
    @SavePointHistory
    public UserPointHistoryResponseDto handleEvent(ReviewEventRequestDto reviewEventRequestDto) {
        Review review = reviewPort.findByIdWithUserAttachedPhotos(reviewEventRequestDto.getReviewId())
                .orElseThrow(ReviewNotFoundException::new);
        Long changePoint = review.modify(reviewEventRequestDto.getContent(),
                reviewEventRequestDto.getPlaceId(),
                reviewEventRequestDto.getAttachedPhotoIds());
        return UserPointHistoryResponseDto.of(review.getUser(), changePoint);
    }
}
