package com.dinamitaexplosivainsana.nojira.domain.exceptions;

public class ConflictWithExistingResourceException extends RuntimeException {
    public ConflictWithExistingResourceException(String message) {
        super(message);
    }
}
