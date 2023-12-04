package com.dinamitaexplosivainsana.nojira.domain.exceptions;
/**
 * Exception thrown when an unauthorized access attempt is detected.
 * This typically occurs when a user attempts an action without the necessary permissions.
 */
public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
