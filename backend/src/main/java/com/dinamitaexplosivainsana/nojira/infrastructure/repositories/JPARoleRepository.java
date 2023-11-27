package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleSchema;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleSchemaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPARoleRepository extends JpaRepository<RoleSchema, RoleSchemaPK> {
    List<RoleSchema> findAllByProjectId(String projectId);

    List<RoleSchema> findAllByUserId(String userId);

    RoleSchema findByUserIdAndProjectId(String userId, String projectId);
}