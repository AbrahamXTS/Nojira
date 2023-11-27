package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.domain.models.StatusCatalog;

public interface StatusCatalogRepository {
    StatusCatalog getStatusByStatusName(String type);

    StatusCatalog saveStatus(StatusCatalog statusCatalog);
}
