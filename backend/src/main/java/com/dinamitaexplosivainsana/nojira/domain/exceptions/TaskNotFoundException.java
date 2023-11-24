package com.dinamitaexplosivainsana.nojira.domain.exceptions;

public class TaskNotFoundException extends RuntimeException {
	public TaskNotFoundException(String message) {
		super(message);
	}
}