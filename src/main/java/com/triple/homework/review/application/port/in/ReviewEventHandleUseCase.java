package com.triple.homework.review.application.port.in;

import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;

public interface ReviewEventHandleUseCase {

    String getCode();

    Long handleEvent(ReviewEventRequestDto reviewEventRequestDto);
}
