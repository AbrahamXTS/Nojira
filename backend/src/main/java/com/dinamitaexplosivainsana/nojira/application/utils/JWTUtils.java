package com.dinamitaexplosivainsana.nojira.application.utils;

import io.jsonwebtoken.Claims;

import java.security.Key;
import java.util.function.Function;
/**
 * Interface that provides methods for the generation, validation, and manipulation of JSON Web Tokens (JWT).
 */
public interface JWTUtils {
    /**
     * Generates a JWT access token based on the provided username.
     *
     * @param username Username for which the token is generated.
     * @return Generated JWT access token.
     */
    String generateAccessToken(String username);
    /**
     * Verifies if a JWT token is valid.
     *
     * @param token JWT token to be validated.
     * @return {@code true} if the token is valid, {@code false} otherwise.
     */

    boolean isValidToken(String token);
    /**
     * Gets the username embedded in a JWT token.
     *
     * @param token JWT token from which the username is to be extracted.
     * @return Extracted username from the token.
     */

    String getUsernameFromToken(String token);

    <T> T getClaim(String token, Function<Claims, T> claimGetterFunction);

    Claims extractAllClaims(String token);
    /**
     * Gets the signature key used to verify the integrity of the JWT token.
     *
     * @return Signature key used to verify the integrity of the JWT token.
     */
    Key getSignatureKey();
}
