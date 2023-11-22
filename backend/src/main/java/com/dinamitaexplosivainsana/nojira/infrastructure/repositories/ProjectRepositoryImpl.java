package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.ProjectRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.Project;
import java.util.Collections;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.ProjectSchema;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectRepositoryImpl implements ProjectRepository {
    private final JPAProjectRepository projectRepository;

    public ProjectRepositoryImpl(JPAProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project getProjectByProjectId(String projectId) {
        ProjectSchema projectSchema = this.projectRepository.getReferenceById(projectId);

        return new Project(
                projectSchema.getId(),
                projectSchema.getName(),
                projectSchema.getDescription()
        );
    }

    @Override
    public List<Project> getAllProjectsByUserId(String userId) {
        return Collections.emptyList();
    }

    @Override
    public Project saveProject(Project project) {
        return null;
    }

    @Override
    public Project deleteProjectByProjectId(String projectId) {
        return null;
    }

    @Override
    public Project updateProjectByProjectId(String projectId, Project project) {
        return null;
    }
}
