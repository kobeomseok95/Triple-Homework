package com.triple.homework.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClientErrorCode implements ErrorEnumCode {

    BIND_EXCEPTION(1001, "입력값에 대한 예외입니다."),
    ;

    private final int code;
    private final String message;
}
