package com.triple.homework.review.adapter.in;

import com.triple.homework.review.adapter.in.request.ReviewEventRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ReviewEventRestController {

    private final ReviewEventDelegator reviewEventDelegator;

    public ReviewEventRestController(ReviewEventDelegator reviewEventDelegator) {
        this.reviewEventDelegator = reviewEventDelegator;
    }

    @PostMapping("/events")
    @ResponseStatus(HttpStatus.OK)
    public void event(@RequestBody @Valid ReviewEventRequest reviewEventRequest) {
        reviewEventDelegator.handle(reviewEventRequest);
    }
}
