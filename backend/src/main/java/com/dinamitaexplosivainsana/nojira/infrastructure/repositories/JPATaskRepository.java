package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.TaskSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPATaskRepository extends JpaRepository<TaskSchema, String> {
    List<TaskSchema> getTaskSchemasByProjectId(String projectId);

    @Query("SELECT t FROM task t WHERE  t.user.id = :userId")
    List<TaskSchema> getAllTasksByUserId(@Param("userId") String userId);

    @Query("SELECT t FROM task t WHERE  t.project.id = :projectId ")
    List<TaskSchema> getAllTasksByProjectId(@Param("projectId") String projectId);
}
