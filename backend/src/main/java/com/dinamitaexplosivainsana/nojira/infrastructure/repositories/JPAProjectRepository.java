package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.ProjectSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPAProjectRepository extends JpaRepository<ProjectSchema, String> {
    @Query("SELECT p FROM project p WHERE p.id IN (SELECT r.project.id FROM role r WHERE r.user.id = :userId)")
    List<ProjectSchema> findProjectsByUserId(@Param("userId") String userId);
}