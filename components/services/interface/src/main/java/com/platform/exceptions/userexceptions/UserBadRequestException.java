package com.platform.exceptions.userexceptions;

import com.platform.exceptions.BadRequestException;

public class UserBadRequestException extends BadRequestException {

    public UserBadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
