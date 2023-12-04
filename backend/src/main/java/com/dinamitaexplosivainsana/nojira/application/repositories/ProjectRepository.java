package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.domain.models.Project;
/**
 * This interface defines methods to access and manipulate data related to projects.
 * It implements operations such as retrieving a project by its ID and saving a new project.
 */
public interface ProjectRepository {
    /**
     * Retrieves a project given its unique identifier.
     *
     * @param projectId The unique identifier of the project to retrieve.
     * @return The Project object associated with the provided identifier, or null if not found.
     */
    Project getProjectByProjectId(String projectId);
     /**
     * Saves a project in the repository.
     *
     * @param project The Project object to be saved in the repository.
     * @return The same Project object after being saved, with possible modifications or assigned ID.
     */
    Project saveProject(Project project);
}
