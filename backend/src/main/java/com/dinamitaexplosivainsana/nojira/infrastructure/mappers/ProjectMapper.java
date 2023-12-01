package com.dinamitaexplosivainsana.nojira.infrastructure.mappers;

import com.dinamitaexplosivainsana.nojira.domain.models.Project;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.ProjectSchema;

public class ProjectMapper {
    private ProjectMapper() {
    }

    public static Project mapToModel(ProjectSchema projectSchema) {
        return new Project(
                projectSchema.getId(),
                projectSchema.getName(),
                projectSchema.getDescription()
        );
    }

    public static ProjectSchema mapToSchema(Project project) {
        return new ProjectSchema(
                project.id(),
                project.name(),
                project.description()
        );
    }
}
