package lib.central.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ResultException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
