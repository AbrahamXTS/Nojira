package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.ProjectRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.Project;
import com.dinamitaexplosivainsana.nojira.infrastructure.mappers.ProjectMapper;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.ProjectSchema;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
    private final JPAProjectRepository projectRepository;

    public ProjectRepositoryImpl(JPAProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project getProjectByProjectId(String projectId) {
        ProjectSchema projectSchema = projectRepository.findById(projectId)
                .orElse(null);

        if (Objects.isNull(projectSchema)) {
            return null;
        }

        return ProjectMapper.mapToModel(projectSchema);
    }

    @Override
    public Project saveProject(Project project) {
        ProjectSchema projectSchema = this.projectRepository
                .save(ProjectMapper.mapToSchema(project));

        return ProjectMapper.mapToModel(projectSchema);
    }
}
