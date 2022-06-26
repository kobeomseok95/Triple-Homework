package com.triple.homework.common.exception.user;

import com.triple.homework.common.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorEnumCode {

    NOT_FOUND_USER(1201, "존재하지 않는 유저입니다."),
    ;

    private final int code;
    private final String message;
}
