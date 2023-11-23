package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.StatusRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.Status;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.StatusCatalogSchema;
import org.springframework.stereotype.Component;

import java.util.Objects;

import java.util.Optional;


@Component
public class StatusRepositoryImpl implements StatusRepository{

    JPAStatusRepository statusRepository;


    public StatusRepositoryImpl(JPAStatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status getStatusByStatusId(Integer id) {
        Optional<StatusCatalogSchema> status = statusRepository.findById(id);
        if (Objects.isNull(status)) {
            return null;
        }
        return new Status(status.get().getId(), status.get().getType());
    }

    @Override
    public Status saveStatus(Status status) {
        StatusCatalogSchema statusCatalogSchema = this.statusRepository.save(
            StatusCatalogSchema.builder()
            .id(status.id())
            .type(status.type())
            .build()
        );

        return new Status(statusCatalogSchema.getId(), statusCatalogSchema.getType());
    }

    @Override
    public Status findStatusById(Integer statusId) {
        StatusCatalogSchema taskStatus = this.statusRepository.findById(statusId)
                .orElse(null);

        if(Objects.isNull(taskStatus)){
            return null;
        }

        return new Status(
                taskStatus.getId(),
                taskStatus.getType()
        );
    }


}

