package com.platform.exceptions;

import javax.swing.*;

public abstract class ApiException extends RuntimeException{

    public ApiException(String errorMessage) {
        super(errorMessage);
    }
}
