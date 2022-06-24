package com.triple.homework.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> applicationException(ApplicationException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest()
                .body(ErrorResponse.ofApplicationException(e));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> bindException(BindException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest()
                .body(ErrorResponse.ofBindException(e.getBindingResult()));
    }
}
