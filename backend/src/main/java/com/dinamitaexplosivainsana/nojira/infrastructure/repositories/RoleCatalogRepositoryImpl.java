package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.RoleCatalogRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.RoleCatalog;
import com.dinamitaexplosivainsana.nojira.infrastructure.mappers.RoleCatalogMapper;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleCatalogSchema;
import org.springframework.stereotype.Repository;

@Repository
public class RoleCatalogRepositoryImpl implements RoleCatalogRepository {
    JPARoleCatalogRepository roleCatalogRepository;

    public RoleCatalogRepositoryImpl(JPARoleCatalogRepository roleCatalogRepository) {
        this.roleCatalogRepository = roleCatalogRepository;
    }

    @Override
    public RoleCatalog saveRoleCatalog(RoleCatalog roleCatalog) {
        RoleCatalogSchema roleCatalogSchema = this.roleCatalogRepository
                .save(RoleCatalogMapper.mapToSchema(roleCatalog));

        return RoleCatalogMapper.mapToModel(roleCatalogSchema);
    }
}