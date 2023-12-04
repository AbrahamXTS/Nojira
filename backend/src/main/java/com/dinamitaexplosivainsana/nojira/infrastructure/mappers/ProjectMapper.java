package com.dinamitaexplosivainsana.nojira.infrastructure.mappers;

import com.dinamitaexplosivainsana.nojira.domain.models.Project;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.ProjectSchema;
/**
 * Mapper class responsible for mapping between {@code Project} domain model and {@code ProjectSchema} data schema.
 */
public class ProjectMapper {
    private ProjectMapper() {
    }
/**
     * Maps a {@code ProjectSchema} instance to a {@code Project} domain model.
     *
     * @param projectSchema The {@code ProjectSchema} instance to be mapped.
     * @return A {@code Project} domain model representing the mapped data.
     */
    public static Project mapToModel(ProjectSchema projectSchema) {
        return new Project(
                projectSchema.getId(),
                projectSchema.getName(),
                projectSchema.getDescription()
        );
    }
/**
     * Maps a {@code Project} domain model to a {@code ProjectSchema} instance.
     *
     * @param project The {@code Project} domain model to be mapped.
     * @return A {@code ProjectSchema} instance representing the mapped data.
     */
    public static ProjectSchema mapToSchema(Project project) {
        return new ProjectSchema(
                project.id(),
                project.name(),
                project.description()
        );
    }
}
