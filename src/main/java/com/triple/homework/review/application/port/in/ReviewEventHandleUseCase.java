package com.triple.homework.review.application.port.in;

import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;
import com.triple.homework.review.application.port.in.response.UserPointHistoryResponseDto;

public interface ReviewEventHandleUseCase {

    String getCode();

    UserPointHistoryResponseDto handleEvent(ReviewEventRequestDto reviewEventRequestDto);
}
