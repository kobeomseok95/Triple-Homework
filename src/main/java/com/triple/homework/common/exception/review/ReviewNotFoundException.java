package com.triple.homework.common.exception.review;

import com.triple.homework.common.exception.ApplicationException;
import com.triple.homework.common.exception.ErrorEnumCode;

public class ReviewNotFoundException extends ApplicationException {

    private static final ErrorEnumCode CODE = ReviewErrorCode.NOT_FOUND;

    public ReviewNotFoundException() {
        super(CODE);
    }
}
