package com.dinamitaexplosivainsana.nojira.domain.exceptions;
/**
 * Exception thrown when a requested resource is not found in the system.
 * This can happen when trying to access or manipulate a resource that does not exist.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
