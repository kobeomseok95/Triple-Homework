package com.triple.homework.review.adapter.in;

import com.triple.homework.review.adapter.in.request.ReviewEventRequest;
import com.triple.homework.review.application.port.in.ReviewEventHandleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewEventDelegator {

    private final List<ReviewEventHandleUseCase> reviewEventHandleUseCases;

    public void handle(ReviewEventRequest reviewEventRequest) {
        reviewEventHandleUseCases.stream()
                .filter(useCase -> useCase.getCode().equals(reviewEventRequest.getAction()))
                .findFirst()
                .orElseThrow()
                .handle(reviewEventRequest.toRequestDto());
    }
}
