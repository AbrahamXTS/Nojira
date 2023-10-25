package com.dinamitaexplosivainsana.nojira.repositories;

 import org.springframework.data.jpa.repository.JpaRepository;
import com.dinamitaexplosivainsana.nojira.schemas.ProjectSchema;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectSchema, String>{
    @Query("SELECT p FROM project p WHERE p.id IN (SELECT r.project.id FROM role r WHERE r.user.id = :userId)")
    List<ProjectSchema> findProjectSchemaByUserId(@Param("userId") String userId);
}