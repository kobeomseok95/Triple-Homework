package com.triple.homework.common.exception.review;

import com.triple.homework.common.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements ErrorEnumCode {

    NOT_FOUND_ACTION(1101, "Action Code를 찾을 수 없습니다."),
    WRITTEN_REVIEW(1102, "이미 장소에 대해 리뷰를 작성했습니다."),
    NOT_FOUND(1103, "작성된 리뷰가 없습니다.")
    ;

    private final int code;
    private final String message;
}
