package com.triple.homework.common.exception.user;

import com.triple.homework.common.exception.ApplicationException;
import com.triple.homework.common.exception.ErrorEnumCode;

public class UserNotFoundException extends ApplicationException {

    private static final ErrorEnumCode CODE = UserErrorCode.NOT_FOUND_USER;

    public UserNotFoundException() {
        super(CODE);
    }
}
