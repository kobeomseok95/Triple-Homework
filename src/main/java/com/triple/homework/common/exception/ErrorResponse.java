package com.triple.homework.common.exception;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    public static ErrorResponse ofApplicationException(ApplicationException e) {
        return new ErrorResponse(e);
    }

    public static ErrorResponse ofBindException(BindingResult bindingResult) {
        return new ErrorResponse(bindingResult);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    static class FieldErrorResponse {

        private String field;
        private String value;
        private String reason;

        private static List<FieldErrorResponse> of(BindingResult bindingResult) {
            return bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> new FieldErrorResponse(
                            error.getField(),
                            error.getRejectedValue() == null ? null : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}
