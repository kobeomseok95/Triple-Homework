package com.triple.homework.common.exception;

public enum ClientErrorCode implements ErrorEnumCode {

    INVALID_REQUEST(1001, "입력값에 대한 예외입니다."),
    ;

    private final int code;
    private final String message;

    private ClientErrorCode(int code, String message) {
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
