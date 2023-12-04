package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import com.dinamitaexplosivainsana.nojira.domain.dto.WrapperResponse;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
/**
 * Controller advice class handling exceptions globally for the application.
 * Extends {@code ResponseEntityExceptionHandler} to provide centralized exception handling.
 *
 * @ControllerAdvice Indicates that this class provides centralized exception handling.
 */
@ControllerAdvice
public class ErrorHandlerController extends ResponseEntityExceptionHandler {
/**
     * Handles exceptions of type {@code ConflictWithExistingResourceException}.
     *
     * @param exception The exception to handle.
     * @return ResponseEntity containing a wrapped response with the exception message and HTTP status code 409 (CONFLICT).
     */
    @ExceptionHandler(ConflictWithExistingResourceException.class)
    public ResponseEntity<WrapperResponse<Void>> conflictWithExistingResourceExceptionHandler(Exception exception) {
        return new ResponseEntity<>(new WrapperResponse<>(false, exception.getMessage(), null), HttpStatus.CONFLICT);
    }
/**
     * Handles exceptions of type {@code InvalidArgumentException}.
     *
     * @param exception The exception to handle.
     * @return ResponseEntity containing a wrapped response with the exception message and HTTP status code 400 (BAD REQUEST).
     */
    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<WrapperResponse<Void>> invalidArgumentExceptionHandler(Exception exception) {
        return new ResponseEntity<>(new WrapperResponse<>(false, exception.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
/**
     * Handles exceptions of type {@code ResourceNotFoundException}.
     *
     * @param exception The exception to handle.
     * @return ResponseEntity containing a wrapped response with the exception message and HTTP status code 404 (NOT FOUND).
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<WrapperResponse<Void>> resourceNotFoundExceptionHandler(Exception exception) {
        return new ResponseEntity<>(new WrapperResponse<>(false, exception.getMessage(), null), HttpStatus.NOT_FOUND);
    }
/**
     * Handles exceptions of type {@code UnauthorizedAccessException}.
     *
     * @param exception The exception to handle.
     * @return ResponseEntity containing a wrapped response with the exception message and HTTP status code 401 (UNAUTHORIZED).
     */
    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<WrapperResponse<Void>> unauthorizedAccessExceptionHandler(Exception exception) {
        return new ResponseEntity<>(new WrapperResponse<>(false, exception.getMessage(), null), HttpStatus.UNAUTHORIZED);
    }
}
