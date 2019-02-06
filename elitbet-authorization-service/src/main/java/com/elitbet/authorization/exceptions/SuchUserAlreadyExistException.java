package com.elitbet.authorization.exceptions;

public class SuchUserAlreadyExistException extends RuntimeException {
    public SuchUserAlreadyExistException(String message) {
        super(message);
    }
}
