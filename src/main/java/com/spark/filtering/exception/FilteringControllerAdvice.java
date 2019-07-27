package com.spark.filtering.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception handler advice class for all SpringMVC controllers.
 *
 * @author MoisesGV
 * @see ControllerAdvice
 *
 */
@ControllerAdvice
public class FilteringControllerAdvice {

    /**
     * Returns whatever HTTP status was set within the ResourceNotFoundException.
     * @param e SpringMVC controller exception.
     * @return http response entity
     * @see ExceptionHandler
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    /**
     * Returns whatever HTTP status was set within the ConstraintViolationException.
     * @param e SpringMVC controller exception.
     * @return http response entity
     * @see ExceptionHandler
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    /**
     * Sets the HTTP status as INTERNAL_SERVER_ERROR (500).
     * @param e SpringMVC controller exception.
     * @return http response entity
     * @see ExceptionHandler
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }

    /**
     * Sets the HTTP status as INTERNAL_SERVER_ERROR (500).
     * @param e SpringMVC controller exception.
     * @return http response entity
     * @see ExceptionHandler
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

}