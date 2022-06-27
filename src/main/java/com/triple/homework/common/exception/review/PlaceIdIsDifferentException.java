package com.triple.homework.common.exception.review;

import com.triple.homework.common.exception.ApplicationException;
import com.triple.homework.common.exception.ErrorEnumCode;

public class PlaceIdIsDifferentException extends ApplicationException {

    private static final ErrorEnumCode CODE = ReviewErrorCode.PLACE_ID_IS_DIFFERENT;

    public PlaceIdIsDifferentException() {
        super(CODE);
    }
}
