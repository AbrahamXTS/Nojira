package com.dinamitaexplosivainsana.nojira.domain.dto;
/**
 *represents project information.
 *
 * @param projectId   Unique identifier of the project.
 * @param projectName Name of the project.
 * @param description Description of the project.
 * @param owner       Owner information represented by {@code OwnerDTO}.
 */
public record ProjectDTO(String projectId, String projectName, String description, OwnerDTO owner) {
}
