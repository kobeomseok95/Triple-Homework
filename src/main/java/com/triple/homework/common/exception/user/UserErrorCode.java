package com.triple.homework.common.exception.user;

import com.triple.homework.common.exception.ErrorEnumCode;

public enum UserErrorCode implements ErrorEnumCode {

    NOT_FOUND_USER(1201, "존재하지 않는 유저입니다."),
    ;

    private final int code;
    private final String message;

    private UserErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
