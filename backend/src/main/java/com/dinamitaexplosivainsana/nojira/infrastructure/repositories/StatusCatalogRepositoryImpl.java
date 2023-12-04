package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.StatusCatalogRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.StatusCatalog;
import com.dinamitaexplosivainsana.nojira.infrastructure.mappers.StatusCatalogMapper;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.StatusCatalogSchema;
import org.springframework.stereotype.Repository;

import java.util.Objects;
/**
 * Implementation of the {@link StatusCatalogRepository} interface using Spring Data JPA.
 * This repository handles CRUD operations on StatusCatalog entities.
 */
@Repository
public class StatusCatalogRepositoryImpl implements StatusCatalogRepository {
    JPAStatusRepository statusRepository;
/**
     * Constructor to initialize the repository with a JPAStatusRepository instance.
     *
     * @param statusRepository The JPA repository for StatusCatalog entities.
     */
    public StatusCatalogRepositoryImpl(JPAStatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }
/**
     * Retrieves a StatusCatalog entity by its type.
     *
     * @param type The type of the StatusCatalog to retrieve.
     * @return The StatusCatalog entity if found, or null if not found.
     */
    @Override
    public StatusCatalog getStatusByStatusName(String type) {
        StatusCatalogSchema statusCatalogSchema = statusRepository.findByType(type);

        if (Objects.isNull(statusCatalogSchema)) {
            return null;
        }

        return StatusCatalogMapper.mapToModel(statusCatalogSchema);
    }
/**
     * Saves a StatusCatalog entity.
     *
     * @param statusCatalog The StatusCatalog entity to be saved.
     * @return The saved StatusCatalog entity.
     */
    @Override
    public StatusCatalog saveStatus(StatusCatalog statusCatalog) {
        StatusCatalogSchema statusCatalogSchema = this.statusRepository
                .save(StatusCatalogMapper.mapToSchema(statusCatalog));

        return StatusCatalogMapper.mapToModel(statusCatalogSchema);
    }
}
