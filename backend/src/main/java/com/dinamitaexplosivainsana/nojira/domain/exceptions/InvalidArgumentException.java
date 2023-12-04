package com.dinamitaexplosivainsana.nojira.domain.exceptions;
/**
 * Exception thrown when an invalid argument is provided.
 * This can happen when a required argument is missing or has an invalid format.
 */
public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String message) {
        super(message);
    }
}
