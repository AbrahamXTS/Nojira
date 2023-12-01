package com.dinamitaexplosivainsana.nojira.domain.dto;

/**
 * Initialize a new project
 *
 * @param title       Name of the project to create
 * @param description This is the description of the project
 */
public record CreateProjectDTO(String title, String description) {
}
