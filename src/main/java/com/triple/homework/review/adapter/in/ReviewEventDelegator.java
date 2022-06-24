package com.triple.homework.review.adapter.in;

import com.triple.homework.review.adapter.in.request.ReviewEventRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewEventDelegator {


    public void handle(ReviewEventRequest reviewEventRequest) {

    }
}
