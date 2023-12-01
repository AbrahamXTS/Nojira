package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import com.dinamitaexplosivainsana.nojira.domain.dto.WrapperResponse;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConflictWithExistingResourceException.class)
    public ResponseEntity<WrapperResponse<Void>> conflictWithExistingResourceExceptionHandler(Exception exception) {
        return new ResponseEntity<>(new WrapperResponse<>(false, exception.getMessage(), null), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<WrapperResponse<Void>> invalidArgumentExceptionHandler(Exception exception) {
        return new ResponseEntity<>(new WrapperResponse<>(false, exception.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<WrapperResponse<Void>> resourceNotFoundExceptionHandler(Exception exception) {
        return new ResponseEntity<>(new WrapperResponse<>(false, exception.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<WrapperResponse<Void>> unauthorizedAccessExceptionHandler(Exception exception) {
        return new ResponseEntity<>(new WrapperResponse<>(false, exception.getMessage(), null), HttpStatus.UNAUTHORIZED);
    }
}
