package com.platform.exceptions.accountexceptions;

import com.platform.exceptions.ResourceAlreadyExistsException;

public class AccountAlreadyExistsException extends ResourceAlreadyExistsException {
    public AccountAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
