package com.dinamitaexplosivainsana.nojira.application.utils;

public interface JWTUtils {
	String generateAccessToken(String username);

	boolean isValidToken(String token);
}
