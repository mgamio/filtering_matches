package com.spark.filtering.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to manage constraints violation
 *
 * @author MoisesGV
 * @See HttpStatus
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ConstraintViolationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ConstraintViolationException() {
        super();
    }

    public ConstraintViolationException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ConstraintViolationException(String message) {
        super(message);
    }

    public ConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }


}