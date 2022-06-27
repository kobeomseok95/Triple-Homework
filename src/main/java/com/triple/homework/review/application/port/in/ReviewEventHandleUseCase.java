package com.triple.homework.review.application.port.in;

import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;
import com.triple.homework.review.application.port.in.response.UserPointHistoryResponse;

public interface ReviewEventHandleUseCase {

    String getCode();

    // TODO: 2022/06/27 kobeomseok95 리턴 타입을 userId, Point를 반환하는 클래스 만들기
    UserPointHistoryResponse handleEvent(ReviewEventRequestDto reviewEventRequestDto);
}
