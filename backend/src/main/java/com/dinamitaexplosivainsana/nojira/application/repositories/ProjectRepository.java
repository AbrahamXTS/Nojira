package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.ProjectSchema;

import java.util.List;

public interface ProjectRepository {
	List<ProjectSchema> findProjectSchemaByUserId(String userId);
}
