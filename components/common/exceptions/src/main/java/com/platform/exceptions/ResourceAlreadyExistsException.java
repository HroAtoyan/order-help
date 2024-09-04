package com.platform.exceptions;

public abstract class ResourceAlreadyExistsException extends RuntimeException{

    public ResourceAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
