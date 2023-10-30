package com.dinamitaexplosivainsana.nojira.domain.exceptions;

public class RequiredArgumentException extends RuntimeException {
	public RequiredArgumentException(String message) {
		super(message);
	}
}
