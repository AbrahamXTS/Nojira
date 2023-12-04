package com.dinamitaexplosivainsana.nojira.domain.dto;
/**
 * User login information.
 *
 * @param email    Email address of the user.
 * @param password Password for user authentication.
 */
public record UserLoginDTO(String email, String password) {
}
