package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.RoleCatalogRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.RoleCatalog;
import com.dinamitaexplosivainsana.nojira.infrastructure.mappers.RoleCatalogMapper;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleCatalogSchema;
import org.springframework.stereotype.Repository;
/**
 * Implementation of the {@link RoleCatalogRepository} interface using Spring Data JPA.
 * This repository handles CRUD operations on RoleCatalog entities.
 */
@Repository
public class RoleCatalogRepositoryImpl implements RoleCatalogRepository {
    JPARoleCatalogRepository roleCatalogRepository;
/**
     * Constructor to initialize the repository with a JPARoleCatalogRepository instance.
     *
     * @param roleCatalogRepository The JPA repository for RoleCatalog entities.
     */
    public RoleCatalogRepositoryImpl(JPARoleCatalogRepository roleCatalogRepository) {
        this.roleCatalogRepository = roleCatalogRepository;
    }
/**
     * Saves a RoleCatalog entity.
     *
     * @param roleCatalog The RoleCatalog entity to be saved.
     * @return The saved RoleCatalog entity.
     */
    @Override
    public RoleCatalog saveRoleCatalog(RoleCatalog roleCatalog) {
        RoleCatalogSchema roleCatalogSchema = this.roleCatalogRepository
                .save(RoleCatalogMapper.mapToSchema(roleCatalog));

        return RoleCatalogMapper.mapToModel(roleCatalogSchema);
    }
}