package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.ProjectRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.Project;
import com.dinamitaexplosivainsana.nojira.infrastructure.mappers.ProjectMapper;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.ProjectSchema;
import org.springframework.stereotype.Repository;

import java.util.Objects;
/**
 * Implementation of the {@link ProjectRepository} interface using Spring Data JPA.
 * This repository handles CRUD operations on Project entities.
 */
@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
    private final JPAProjectRepository projectRepository;
/**
     * Constructor to initialize the repository with a JPAProjectRepository instance.
     *
     * @param projectRepository The JPA repository for Project entities.
     */
    public ProjectRepositoryImpl(JPAProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
/**
     * Retrieves a Project entity by its projectId.
     *
     * @param projectId The identifier of the Project to retrieve.
     * @return The Project entity if found, or null if not found.
     */
    @Override
    public Project getProjectByProjectId(String projectId) {
        ProjectSchema projectSchema = projectRepository.findById(projectId)
                .orElse(null);

        if (Objects.isNull(projectSchema)) {
            return null;
        }

        return ProjectMapper.mapToModel(projectSchema);
    }
/**
     * Saves a Project entity.
     *
     * @param project The Project entity to be saved.
     * @return The saved Project entity.
     */
    @Override
    public Project saveProject(Project project) {
        ProjectSchema projectSchema = this.projectRepository
                .save(ProjectMapper.mapToSchema(project));

        return ProjectMapper.mapToModel(projectSchema);
    }
}
