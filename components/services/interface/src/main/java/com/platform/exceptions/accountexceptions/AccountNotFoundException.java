package com.platform.exceptions.accountexceptions;

import com.platform.exceptions.ResourceNotFoundException;

public class AccountNotFoundException extends ResourceNotFoundException {
    public AccountNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
