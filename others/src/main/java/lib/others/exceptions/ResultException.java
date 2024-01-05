package lib.others.exceptions;

import org.springframework.http.HttpStatus;

public class ResultException extends Exception {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    public ResultException(String message) {
        super(message);
    }

    public ResultException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
