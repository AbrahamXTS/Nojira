package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.StatusCatalogRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.StatusCatalog;
import com.dinamitaexplosivainsana.nojira.infrastructure.mappers.StatusCatalogMapper;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.StatusCatalogSchema;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class StatusCatalogRepositoryImpl implements StatusCatalogRepository {
    JPAStatusRepository statusRepository;

    public StatusCatalogRepositoryImpl(JPAStatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public StatusCatalog getStatusByStatusName(String type) {
        StatusCatalogSchema statusCatalogSchema = statusRepository.findByType(type);

        if (Objects.isNull(statusCatalogSchema)) {
            return null;
        }

        return StatusCatalogMapper.mapToModel(statusCatalogSchema);
    }

    @Override
    public StatusCatalog saveStatus(StatusCatalog statusCatalog) {
        StatusCatalogSchema statusCatalogSchema = this.statusRepository
                .save(StatusCatalogMapper.mapToSchema(statusCatalog));

        return StatusCatalogMapper.mapToModel(statusCatalogSchema);
    }
}
