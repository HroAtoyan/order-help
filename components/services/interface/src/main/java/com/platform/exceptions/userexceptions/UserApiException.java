package com.platform.exceptions.userexceptions;

import com.platform.exceptions.ApiException;

public class UserApiException extends ApiException {

    public UserApiException(String errorMessage) {
        super(errorMessage);
    }
}
