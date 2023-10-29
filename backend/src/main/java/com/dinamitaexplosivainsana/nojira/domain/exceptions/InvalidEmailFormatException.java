package com.dinamitaexplosivainsana.nojira.domain.exceptions;

public class InvalidEmailFormatException extends RuntimeException {
	public InvalidEmailFormatException(String message) {
		super(message);
	}
}
