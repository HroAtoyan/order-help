package com.platform.exceptions.accountexceptions;

import com.platform.exceptions.BadRequestException;

public class AccountBadRequestException extends BadRequestException {

    public AccountBadRequestException(String errorMessage) {
        super(errorMessage);
    }

}
