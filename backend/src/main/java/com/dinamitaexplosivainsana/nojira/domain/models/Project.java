package com.dinamitaexplosivainsana.nojira.domain.models;
/**
 * Represents a project in the system.
 *
 * @param id          Unique identifier of the project.
 * @param name        Name of the project.
 * @param description Description of the project.
 */
public record Project(String id, String name, String description) {
}
