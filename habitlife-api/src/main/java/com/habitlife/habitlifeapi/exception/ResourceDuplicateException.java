package com.habitlife.habitlifeapi.exception;

public class ResourceDuplicateException extends RuntimeException{
    public ResourceDuplicateException() {
    }

    public ResourceDuplicateException(String message) {
        super(message);
    }
}
