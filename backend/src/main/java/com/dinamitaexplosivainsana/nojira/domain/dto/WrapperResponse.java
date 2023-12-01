package com.dinamitaexplosivainsana.nojira.domain.dto;

public record WrapperResponse<T>(boolean ok, String message, T body) {
}
