package com.dinamitaexplosivainsana.nojira.domain.exceptions;

/**
 * UserNotFoundException is thrown when a user cannot be found while
 * the program is running.
 *
 * @see java.lang.RuntimeException
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
