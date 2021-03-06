package com.triple.homework.common.exception;

public class ApplicationException extends RuntimeException {

    private ErrorEnumCode errorEnumCode;

    public ApplicationException(ErrorEnumCode errorEnumCode) {
        super(errorEnumCode.getMessage());
        this.errorEnumCode = errorEnumCode;
    }

    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public ErrorEnumCode getErrorEnumCode() {
        return this.errorEnumCode;
    }
}
