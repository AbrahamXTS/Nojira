package com.dinamitaexplosivainsana.nojira.infrastructure.security;

import com.dinamitaexplosivainsana.nojira.application.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;
/**
 * Implementation of the JWTUtils interface providing JWT functionality.
 */
@Component
public class JWTUtilsImpl implements JWTUtils {

	@Value("${jwt.secret.key}")
	private String secretKey;

	@Value("${jwt.expiration.time}")
	private String timeExpiration;
    /**
	 * Generates an access token for the specified username.
	 *
	 * @param username The username for which the token is generated.
	 * @return The generated access token.
	 */
	public String generateAccessToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
				.signWith(getSignatureKey(), SignatureAlgorithm.HS256)
				.compact();
	}
    /**
	 * Validates whether the provided token is a valid JWT.
	 *
	 * @param token The JWT to be validated.
	 * @return True if the token is valid; false otherwise.
	 */
	public boolean isValidToken(String token) {
		boolean isValidToken = true;

		try {
			Jwts.parserBuilder()
					.setSigningKey(getSignatureKey())
					.build()
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			isValidToken = false;
		}

		return isValidToken;
	}
    /**
	 * Retrieves the username from the provided JWT.
	 *
	 * @param token The JWT from which to retrieve the username.
	 * @return The username.
	 */
	public String getUsernameFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}
    /**
	 * Retrieves a specific claim from the provided JWT using a claim getter function.
	 *
	 * @param token               The JWT from which to retrieve the claim.
	 * @param claimGetterFunction The function to get the desired claim from the JWT.
	 * @param <T>                 The type of the claim.
	 * @return The retrieved claim.
	 */
	public <T> T getClaim(String token, Function<Claims, T> claimGetterFunction) {
		Claims claims = extractAllClaims(token);
		return claimGetterFunction.apply(claims);
	}
	/**
	 * Extracts all claims from the provided JWT.
	 *
	 * @param token The JWT from which to extract claims.
	 * @return The extracted claims.
	 */
	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignatureKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
    /**
	 * Retrieves the signature key used for JWT signing.
	 *
	 * @return The signature key.
	 */
	public Key getSignatureKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
