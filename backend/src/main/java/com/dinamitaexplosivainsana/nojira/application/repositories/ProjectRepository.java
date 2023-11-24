package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.domain.models.Project;
import com.dinamitaexplosivainsana.nojira.domain.models.Task;

import java.util.List;

public interface ProjectRepository {
    Project getProjectByProjectId(String projectId);

    List<Project> getAllProjectsByUserId(String userId);

    List<Task> getAllTasksByProjectId(String projectId);

    Project saveProject(Project project);

    Project deleteProjectByProjectId(String projectId);

    Project updateProjectByProjectId(String projectId, Project project);
}
