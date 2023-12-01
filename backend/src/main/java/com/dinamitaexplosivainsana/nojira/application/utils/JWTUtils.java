package com.dinamitaexplosivainsana.nojira.application.utils;

import io.jsonwebtoken.Claims;

import java.security.Key;
import java.util.function.Function;

public interface JWTUtils {
    String generateAccessToken(String username);

    boolean isValidToken(String token);

    String getUsernameFromToken(String token);

    <T> T getClaim(String token, Function<Claims, T> claimGetterFunction);

    Claims extractAllClaims(String token);

    Key getSignatureKey();
}
