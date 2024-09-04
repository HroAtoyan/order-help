package com.platform.exceptions.userexceptions;

import com.platform.exceptions.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {


    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
