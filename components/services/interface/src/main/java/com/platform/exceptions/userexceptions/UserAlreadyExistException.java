package com.platform.exceptions.userexceptions;

import com.platform.exceptions.ResourceAlreadyExistsException;

public class UserAlreadyExistException extends ResourceAlreadyExistsException {

    public UserAlreadyExistException(String errormessage) {
        super(errormessage);
    }
}
