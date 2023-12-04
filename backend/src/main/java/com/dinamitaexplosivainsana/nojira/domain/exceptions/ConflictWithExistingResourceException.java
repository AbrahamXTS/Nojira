package com.dinamitaexplosivainsana.nojira.domain.exceptions;
/**
 * Exception thrown when there is a conflict with an existing resource.
 * This typically occurs when trying to create a resource that already exists.
 */

public class ConflictWithExistingResourceException extends RuntimeException {
    public ConflictWithExistingResourceException(String message) {
        super(message);
    }
}
