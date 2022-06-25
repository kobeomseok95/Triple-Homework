package com.triple.homework.common.exception.review;

import com.triple.homework.common.exception.ApplicationException;
import com.triple.homework.common.exception.ErrorEnumCode;

public class WrittenReviewByUserAndPlaceException extends ApplicationException {

    private static final ErrorEnumCode CODE = ReviewErrorCode.WRITTEN_REVIEW;

    public WrittenReviewByUserAndPlaceException() {
        super(CODE);
    }
}
