package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.StatusRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.Status;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.StatusCatalogSchema;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StatusRepositoryImpl implements StatusRepository{

    JPAStatusRepository statusRepository;

    public StatusRepositoryImpl(JPAStatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }


    @Override
    public Status findStatusById(Integer statusId) {
        StatusCatalogSchema taskStatus = this.statusRepository.findById(Integer.valueOf(statusId))
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
