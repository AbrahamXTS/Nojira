package com.dinamitaexplosivainsana.nojira.infrastructure.security;

import com.dinamitaexplosivainsana.nojira.application.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
/**
 * Custom Spring Security filter for JWT authorization.
 * This filter intercepts requests, extracts JWT from the "Authorization" header,
 * validates the token, and sets up Spring Security authentication if the token is valid.
 */
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
	private final JWTUtils jwtUtils;
	private final UserDetailsService userDetailsService;
     /**
	 * Constructor to initialize the JWTAuthorizationFilter with JWTUtils and UserDetailsService.
	 *
	 * @param jwtUtils            Utility class for JWT operations.
	 * @param userDetailsService  Service for loading user details by username.
	 */
	public JWTAuthorizationFilter(
			JWTUtils jwtUtils,
			UserDetailsServiceImpl userDetailsService
	) {
		this.jwtUtils = jwtUtils;
		this.userDetailsService = userDetailsService;
	}
	/**
	 * Performs the actual filtering for JWT authorization.
	 *
	 * @param request       The HTTP request.
	 * @param response      The HTTP response.
	 * @param filterChain   The filter chain.
	 * @throws ServletException If a servlet-related error occurs.
	 * @throws IOException      If an I/O error occurs.
	 */

	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain
	) throws ServletException, IOException {
		String bearerToken = request.getHeader("Authorization");

		if (Objects.nonNull(bearerToken) && bearerToken.startsWith("Bearer ")) {
			String token = bearerToken.substring(7);

			if (jwtUtils.isValidToken(token)) {
				String username = jwtUtils.getUsernameFromToken(token);
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);

				UsernamePasswordAuthenticationToken authenticationToken =
						new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());

				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}

		filterChain.doFilter(request, response);
	}
}