package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.TaskSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * JPA repository interface for performing CRUD operations on {@link TaskSchema} entities.
 * Extends {@link JpaRepository} to benefit from Spring Data JPA features.
 */
@Repository
public interface JPATaskRepository extends JpaRepository<TaskSchema, String> {
     /**
     * Retrieves a list of task schemas based on the specified project ID.
     *
     * @param projectId The ID of the project.
     * @return A list of task schemas associated with the specified project.
     */
    List<TaskSchema> getTaskSchemasByProjectId(String projectId);
 /**
     * Retrieves a list of task schemas based on the specified user ID.
     *
     * @param userId The ID of the user.
     * @return A list of task schemas associated with the specified user.
     */
    @Query("SELECT t FROM task t WHERE  t.user.id = :userId")
    List<TaskSchema> getAllTasksByUserId(@Param("userId") String userId);
     /**
     * Retrieves a list of task schemas based on the specified project ID.
     *
     * @param projectId The ID of the project.
     * @return A list of task schemas associated with the specified project.
     */
    @Query("SELECT t FROM task t WHERE  t.project.id = :projectId ")
    List<TaskSchema> getAllTasksByProjectId(@Param("projectId") String projectId);
}
