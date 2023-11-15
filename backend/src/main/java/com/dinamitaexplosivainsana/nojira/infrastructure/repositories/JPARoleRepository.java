package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RolePK;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPARoleRepository extends JpaRepository<RoleSchema, RolePK> {
    List<RoleSchema> findAllByProjectId(String projectId);

    List<RoleSchema> findAllByUserId(String userId);

//    @Query("SELECT r FROM role r WHERE r.user.id = :userId")
//    List<RoleSchema> findAllByUserId(@Param("userId") String userId);
}
