package elitbet.com.authorization.controller.exception;

import elitbet.com.authorization.exceptions.SuchUserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

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
    public ResponseEntity<ErrorDetails> handle(SuchUserAlreadyExistException ex){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.message = ex.getMessage();
        errorDetails.code = HttpStatus.UNPROCESSABLE_ENTITY.value();
        return new ResponseEntity<>(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
