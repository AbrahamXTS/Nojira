package com.dinamitaexplosivainsana.nojira.domain.dto;
/**
 * Generic wrapper response DTO that encapsulates the response status, message, and body.
 *
 * @param <T> Type of the body content.
 * @param ok      Boolean indicating the success status of the response.
 * @param message Descriptive message associated with the response.
 * @param body    Body content of the response.
 */
public record WrapperResponse<T>(boolean ok, String message, T body) {
}
