package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.domain.models.Status;

public interface StatusRepository {
    Status saveStatus(Status status); 
    
    Status findStatusById(Integer statusId);
}
