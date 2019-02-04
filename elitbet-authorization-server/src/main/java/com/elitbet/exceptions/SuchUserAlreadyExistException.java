package com.elitbet.exceptions;

public class SuchUserAlreadyExistException extends RuntimeException {
    public SuchUserAlreadyExistException(String message) {
        super(message);
    }
}
