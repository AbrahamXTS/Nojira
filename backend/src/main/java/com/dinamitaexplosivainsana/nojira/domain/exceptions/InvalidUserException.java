package com.dinamitaexplosivainsana.nojira.domain.exceptions;

public class InvalidUserException extends RuntimeException{
    public InvalidUserException(String message) {
        super(message);
    }

}
