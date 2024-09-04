package com.platform.exceptions.accountexceptions;

import com.platform.exceptions.ApiException;

public class AccountApiException extends ApiException {
    public AccountApiException(String errorMessage) {
        super(errorMessage);
    }
}
