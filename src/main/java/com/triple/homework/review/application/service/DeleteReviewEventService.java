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
class DeleteReviewEventService implements ReviewEventHandleUseCase {

    private final ReviewPort reviewPort;

    public DeleteReviewEventService(ReviewPort reviewPort) {
        this.reviewPort = reviewPort;
    }

    @Override
    public String getCode() {
        return "DELETE";
    }

    @Override
    @SavePointHistory
    public UserPointHistoryResponseDto handleEvent(ReviewEventRequestDto reviewEventRequestDto) {
        Review review = reviewPort.findByIdWithUserAttachedPhotos(reviewEventRequestDto.getReviewId())
                .orElseThrow(ReviewNotFoundException::new);
        review.decreaseUsersPointAndReturnReviewPoint();
        reviewPort.delete(review);
        return UserPointHistoryResponseDto.of(review.getUser(), -review.getReviewPoints());
    }
}
