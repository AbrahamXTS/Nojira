package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;
package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RolePK;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JPARoleRepository extends JpaRepository<RoleSchema, RolePK> {
    @Query("SELECT r FROM role r WHERE  r.user.id = :userId AND r.project.id = :projectId")
    RoleSchema findByUserIdAndProjectId(@Param("userId") String userId, @Param("projectId") String projectId);
}