package com.dinamitaexplosivainsana.nojira.infrastructure.mappers;

import com.dinamitaexplosivainsana.nojira.domain.models.RoleCatalog;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleCatalogSchema;
/**
 * Mapper class responsible for converting between {@link RoleCatalog} domain models
 * and {@link RoleCatalogSchema} database schemas.
 */
public class RoleCatalogMapper {
    private RoleCatalogMapper() {
    }
/**
     * Maps a {@link RoleCatalogSchema} object to a {@link RoleCatalog} domain model.
     *
     * @param roleCatalogSchema The database schema representing a role catalog.
     * @return A corresponding domain model representing the role catalog.
     */
    public static RoleCatalog mapToModel(RoleCatalogSchema roleCatalogSchema) {
        return new RoleCatalog(
                roleCatalogSchema.getId(),
                roleCatalogSchema.getType()
        );
    }
 /**
     * Maps a {@link RoleCatalog} domain model to a {@link RoleCatalogSchema} database schema.
     *
     * @param roleCatalog The domain model representing a role catalog.
     * @return A corresponding database schema representing the role catalog.
     */
    public static RoleCatalogSchema mapToSchema(RoleCatalog roleCatalog) {
        return new RoleCatalogSchema(
                roleCatalog.id(),
                roleCatalog.type()
        );
    }
}
