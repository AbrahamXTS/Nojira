package com.dinamitaexplosivainsana.nojira.domain.dto;
/**
 * represents information for creating a new task.
 *
 * @param title       Title of the new task.
 * @param description Description of the new task.
 */
public record TaskCreateDTO(String title, String description) {
}
