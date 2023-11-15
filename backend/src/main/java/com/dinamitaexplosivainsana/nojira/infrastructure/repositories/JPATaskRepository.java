package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.TaskSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPATaskRepository extends JpaRepository<TaskSchema, String> {

    List<TaskSchema> getAllByUserId(String userId);
    List<TaskSchema> getTaskSchemasByProject_Id(String projectId);
}
