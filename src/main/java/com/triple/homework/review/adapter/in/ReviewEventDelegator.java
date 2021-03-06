package com.triple.homework.review.adapter.in;

import com.triple.homework.common.exception.review.EventActionNotFoundException;
import com.triple.homework.review.adapter.in.request.ReviewEventRequest;
import com.triple.homework.review.application.port.in.ReviewEventHandleUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewEventDelegator {

    private final List<ReviewEventHandleUseCase> reviewEventHandleUseCases;

    public ReviewEventDelegator(List<ReviewEventHandleUseCase> reviewEventHandleUseCases) {
        this.reviewEventHandleUseCases = reviewEventHandleUseCases;
    }

    public void handle(ReviewEventRequest reviewEventRequest) {
        reviewEventHandleUseCases.stream()
                .filter(useCase -> useCase.getCode().equals(reviewEventRequest.getAction()))
                .findFirst()
                .orElseThrow(EventActionNotFoundException::new)
                .handleEvent(reviewEventRequest.toRequestDto());
    }
}
