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
        this.code = 100;
        this.errors = Collections.emptyList();
    }

    public static ErrorResponse of(ApplicationException e) {
        return new ErrorResponse(e);
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
