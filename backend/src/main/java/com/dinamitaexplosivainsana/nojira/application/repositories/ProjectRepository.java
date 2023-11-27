package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.domain.models.Project;

public interface ProjectRepository {
    Project getProjectByProjectId(String projectId);

    Project saveProject(Project project);
}
