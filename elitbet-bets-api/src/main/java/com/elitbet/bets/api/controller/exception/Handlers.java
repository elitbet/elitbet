package com.elitbet.bets.api.controller.exception;

import com.elitbet.bets.api.exceptions.SuchUserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.NoSuchElementException;

@ControllerAdvice
@RestController
public class Handlers {

    private class ErrorDetails implements Serializable {
        String message;
        int code;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    @ExceptionHandler(value = {SuchUserAlreadyExistException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDetails handleSuchUserAlreadyExistException(SuchUserAlreadyExistException ex){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.message = ex.getMessage();
        errorDetails.code = HttpStatus.UNPROCESSABLE_ENTITY.value();
        return errorDetails;
    }

    @ExceptionHandler(value = {InvalidParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleInvalidParameterException(InvalidParameterException ex){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setMessage(ex.getMessage());
        return errorDetails;
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails handleNoSuchElementException(NoSuchElementException ex){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setCode(HttpStatus.NOT_FOUND.value());
        errorDetails.setMessage(ex.getMessage());
        return errorDetails;
    }
}
