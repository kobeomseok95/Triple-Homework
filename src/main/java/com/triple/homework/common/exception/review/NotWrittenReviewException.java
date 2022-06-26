package com.triple.homework.common.exception.review;

import com.triple.homework.common.exception.ApplicationException;
import com.triple.homework.common.exception.ErrorEnumCode;

public class NotWrittenReviewException extends ApplicationException {

    private static final ErrorEnumCode CODE = ReviewErrorCode.NOT_WRITTEN_REVIEW;

    public NotWrittenReviewException() {
        super(CODE);
    }
}
