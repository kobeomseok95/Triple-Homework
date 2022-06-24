package com.triple.homework.common.exception.review;

import com.triple.homework.common.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActionErrorCode implements ErrorEnumCode {

    NOT_FOUND_ACTION(1101, "Action Code를 찾을 수 없습니다.");

    private final int code;
    private final String message;
}
