package com.triple.homework.common.exception.review;

import com.triple.homework.common.exception.ApplicationException;
import com.triple.homework.common.exception.ErrorEnumCode;

public class EventActionNotFoundException extends ApplicationException {

    private static final ErrorEnumCode CODE = ActionErrorCode.NOT_FOUND_ACTION;

    public EventActionNotFoundException() {
        super(CODE);
    }
}
