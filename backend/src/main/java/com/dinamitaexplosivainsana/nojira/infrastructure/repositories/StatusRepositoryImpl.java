package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.StatusRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.Status;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.StatusCatalogSchema;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class StatusRepositoryImpl implements StatusRepository {

    private final JPAStatusRepository statusRepository;

    public StatusRepositoryImpl(JPAStatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }


    @Override
    public Status getStatusByStatusId(Integer id) {
        Optional<StatusCatalogSchema> status = statusRepository.findById(id);
        if(Objects.isNull(status)){
            return null;
        }
        return new Status(status.get().getId(),status.get().getType());
    }
}
