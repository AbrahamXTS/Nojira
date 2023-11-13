package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.RoleRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.Project;
import com.dinamitaexplosivainsana.nojira.domain.models.Role;
import com.dinamitaexplosivainsana.nojira.domain.models.RoleCatalog;
import com.dinamitaexplosivainsana.nojira.domain.models.User;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleSchema;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RoleRepositoryImpl implements RoleRepository {

    JPARoleRepository roleRepository;

    public RoleRepositoryImpl(JPARoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }


    @Override
    public Role findRoleBetweenUserAndProject(String userId, String projectId) {
        RoleSchema roleSchema = this.roleRepository.findByUserIdAndProjectId(userId, projectId);

        if(Objects.isNull(roleSchema)){
            return null;
        }

        return new Role(
                new RoleCatalog(
                        roleSchema.getRoleCatalog().getId(),
                        roleSchema.getRoleCatalog().getType()
                ),
                new User(
                        roleSchema.getUser().getId(),
                        roleSchema.getUser().getFullName(),
                        roleSchema.getUser().getEmail(),
                        roleSchema.getUser().getPassword()
                ),
                new Project(
                        roleSchema.getProject().getId(),
                        roleSchema.getProject().getName(),
                        roleSchema.getProject().getDescription()
                )
        );
    }
}
