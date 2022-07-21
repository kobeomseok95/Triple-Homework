package com.triple.homework.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ErrorResponse {

    private LocalDateTime time;
    private String message;
    private int status;
    private int code;
    private List<FieldErrorResponse> errors;

    private ErrorResponse(ApplicationException e) {
        this.time = LocalDateTime.now();
        this.status = HttpStatus.BAD_REQUEST.value();
        this.message = e.getMessage();
        this.code = e.getErrorEnumCode().getCode();
        this.errors = Collections.emptyList();
    }

    private ErrorResponse(BindingResult bindingResult) {
        this.time = LocalDateTime.now();
        this.status = HttpStatus.BAD_REQUEST.value();
        this.message = ClientErrorCode.INVALID_REQUEST.getMessage();
        this.code = ClientErrorCode.INVALID_REQUEST.getCode();
        this.errors = FieldErrorResponse.of(bindingResult);
    }

    private ErrorResponse(LocalDateTime time, String message, int status, int code, List<FieldErrorResponse> errors) {
        this.time = time;
        this.message = message;
        this.status = status;
        this.code = code;
        this.errors = errors;
    }

    private ErrorResponse() {
    }

    public static ErrorResponse ofApplicationException(ApplicationException e) {
        return new ErrorResponse(e);
    }

    public static ErrorResponse ofBindException(BindingResult bindingResult) {
        return new ErrorResponse(bindingResult);
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public String getMessage() {
        return this.message;
    }

    public int getStatus() {
        return this.status;
    }

    public int getCode() {
        return this.code;
    }

    public List<FieldErrorResponse> getErrors() {
        return this.errors;
    }

    static class FieldErrorResponse {

        private String field;
        private String value;
        private String reason;

        private FieldErrorResponse(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        private FieldErrorResponse() {
        }

        private static List<FieldErrorResponse> of(BindingResult bindingResult) {
            return bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> new FieldErrorResponse(
                            error.getField(),
                            error.getRejectedValue() == null ? null : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }

        public static FieldErrorResponseBuilder builder() {
            return new FieldErrorResponseBuilder();
        }

        public String getField() {
            return this.field;
        }

        public String getValue() {
            return this.value;
        }

        public String getReason() {
            return this.reason;
        }

        public static class FieldErrorResponseBuilder {
            private String field;
            private String value;
            private String reason;

            FieldErrorResponseBuilder() {
            }

            public FieldErrorResponseBuilder field(String field) {
                this.field = field;
                return this;
            }

            public FieldErrorResponseBuilder value(String value) {
                this.value = value;
                return this;
            }

            public FieldErrorResponseBuilder reason(String reason) {
                this.reason = reason;
                return this;
            }

            public FieldErrorResponse build() {
                return new FieldErrorResponse(field, value, reason);
            }

            public String toString() {
                return "ErrorResponse.FieldErrorResponse.FieldErrorResponseBuilder(field=" + this.field + ", value=" + this.value + ", reason=" + this.reason + ")";
            }
        }
    }
}
