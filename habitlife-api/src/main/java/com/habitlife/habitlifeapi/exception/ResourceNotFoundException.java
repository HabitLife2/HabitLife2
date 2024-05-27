package com.habitlife.habitlifeapi.exception;

public class ResourceNotFoundException extends  RuntimeException {

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
