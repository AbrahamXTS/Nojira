package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.domain.models.RoleCatalog;
/**
 * This interface defines methods to access and manipulate data related to the role catalog.
 * It implements operations such as saving a new role in the catalog.
 */
public interface RoleCatalogRepository {
    /**
     * Saves a new role in the role catalog.
     *
     * @param roleCatalog The RoleCatalog object to be saved in the catalog.
     * @return The same RoleCatalog object after being saved, with possible modifications or assigned ID.
     */
    RoleCatalog saveRoleCatalog (RoleCatalog roleCatalog); 
} 
