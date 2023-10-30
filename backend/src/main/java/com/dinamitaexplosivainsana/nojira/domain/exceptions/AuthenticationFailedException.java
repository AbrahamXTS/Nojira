package com.dinamitaexplosivainsana.nojira.domain.exceptions;

public class AuthenticationFailedException extends RuntimeException {
	public AuthenticationFailedException(String message) {
		super(message);
	}
}
