package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.domain.models.StatusCatalog;
/**
 * Interface that provides methods to access and manipulate the status catalog.
 */
public interface StatusCatalogRepository {
    /**
     * Retrieves a {@code StatusCatalog} object based on the provided status name.
     *
     * @param type The name of the status in the catalog to retrieve.
     * @return The {@code StatusCatalog} object corresponding to the provided status name.
     */
    StatusCatalog getStatusByStatusName(String type);
    /**
     * Saves a new status in the catalog.
     *
     * @param statusCatalog The {@code StatusCatalog} object to be saved in the catalog.
     * @return The {@code StatusCatalog} object that has been saved in the catalog.
     */
    StatusCatalog saveStatus(StatusCatalog statusCatalog);
}
