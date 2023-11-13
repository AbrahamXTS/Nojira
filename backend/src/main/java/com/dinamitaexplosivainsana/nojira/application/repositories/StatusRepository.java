package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.domain.models.Status;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository {
    Status findStatusById(Integer statusId);
}
