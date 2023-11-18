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

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<WrapperResponse<Void>> authenticationFailedExceptionHandler(Exception e) {
        return new ResponseEntity<>(new WrapperResponse<>(false, e.getMessage(), null), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<WrapperResponse<Void>> userAlreadyExistsExceptionHandler(Exception e) {
        return new ResponseEntity<>(new WrapperResponse<>(false, e.getMessage(), null), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {RequiredArgumentException.class, InvalidEmailFormatException.class, InvalidFullNameFormatException.class})
    public ResponseEntity<WrapperResponse<Void>> invalidArgumentExceptionHandler(Exception e) {
        return new ResponseEntity<>(new WrapperResponse<>(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    public ResponseEntity<WrapperResponse<Void>> unauthorizedUserExceptionHandler(Exception e){
        return new ResponseEntity<>(new WrapperResponse<>(false, e.getMessage(), null), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<WrapperResponse<Void>> taskNotFoundExceptionHandler(Exception e){
        return new ResponseEntity<>(new WrapperResponse<>(false, e.getMessage(),null), HttpStatus.NOT_FOUND);
    }
}
