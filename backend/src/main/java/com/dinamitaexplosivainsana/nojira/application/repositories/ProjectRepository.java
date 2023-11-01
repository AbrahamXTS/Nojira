package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.domain.models.Project;

import java.util.List;

public interface ProjectRepository {
    Project getProjectByProjectId(String projectId);

    List<Project> getAllProjectsByUserId(String userId);

    Project saveProject(Project project);

    Project deleteProjectByProjectId(String projectId);

    Project updateProjectByProjectId(String projectId, Project project);
}
