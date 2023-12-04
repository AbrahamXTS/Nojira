package com.dinamitaexplosivainsana.nojira.domain.dto;
/**
 * Signup information.
 *
 * @param fullName Full name of the user.
 * @param email    Email address of the user.
 * @param password Password for user authentication.
 */
public record UserSignupDTO(String fullName, String email, String password) {
}
