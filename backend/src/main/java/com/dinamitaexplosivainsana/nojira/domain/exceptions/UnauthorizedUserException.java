package com.dinamitaexplosivainsana.nojira.domain.exceptions;

public class UnauthorizedUserException extends RuntimeException {
    public UnauthorizedUserException(String message) {
        super(message);
    }

}
