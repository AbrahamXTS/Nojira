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

@Component
public class JWTUtilsImpl implements JWTUtils {

	@Value("${jwt.secret.key}")
	private String secretKey;

	@Value("${jwt.expiration.time}")
	private String timeExpiration;

	public String generateAccessToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
				.signWith(getSignatureKey(), SignatureAlgorithm.HS256)
				.compact();
	}

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

	public String getUsernameFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}

	public <T> T getClaim(String token, Function<Claims, T> claimGetterFunction) {
		Claims claims = extractAllClaims(token);
		return claimGetterFunction.apply(claims);
	}

	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignatureKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	public Key getSignatureKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
