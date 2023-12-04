package com.dinamitaexplosivainsana.nojira.domain.dto;
/**
 * represents successful authentication information.
 *
 * @param userId   Unique identifier of the user.
 * @param fullName Full name of the user.
 * @param email    Email of the user.
 * @param token    Authentication token generated for the user.
 */
public record SuccessfulAuthenticationDTO(String userId, String fullName, String email, String token) {
}
