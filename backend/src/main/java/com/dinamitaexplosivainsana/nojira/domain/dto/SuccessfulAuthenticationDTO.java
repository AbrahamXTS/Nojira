package com.dinamitaexplosivainsana.nojira.domain.dto;

public record SuccessfulAuthenticationDTO(String userId, String fullName, String email, String token) {
}
