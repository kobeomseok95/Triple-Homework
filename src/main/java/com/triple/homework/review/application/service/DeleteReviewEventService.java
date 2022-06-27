package com.triple.homework.review.application.service;

import com.triple.homework.common.exception.review.ReviewNotFoundException;
import com.triple.homework.review.application.port.in.ReviewEventHandleUseCase;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;
import com.triple.homework.review.application.port.out.ReviewPort;
import com.triple.homework.review.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
class DeleteReviewEventService implements ReviewEventHandleUseCase {

    private final ReviewPort reviewPort;

    @Override
    public String getCode() {
        return "DELETE";
    }

    @Override
    public void handleEvent(ReviewEventRequestDto reviewEventRequestDto) {
        Review review = reviewPort.findByIdWithUserAttachedPhotos(reviewEventRequestDto.getReviewId())
                .orElseThrow(ReviewNotFoundException::new);
        review.decreaseUsersPoint();
        reviewPort.delete(review);
    }
}
