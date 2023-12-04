package com.dinamitaexplosivainsana.nojira.infrastructure.mappers;

import com.dinamitaexplosivainsana.nojira.domain.models.StatusCatalog;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.StatusCatalogSchema;
/**
 * Mapper class responsible for converting between {@link StatusCatalog} domain models
 * and {@link StatusCatalogSchema} database schemas.
 */
public class StatusCatalogMapper {
    /**
     * Private constructor to prevent instantiation of the mapper class.
     * All methods in this class are static, and it should not be instantiated.
     */
    private StatusCatalogMapper() {
    }
/**
     * Maps a {@link StatusCatalogSchema} object to a {@link StatusCatalog} domain model.
     *
     * @param statusCatalogSchema The database schema representing a status catalog.
     * @return A corresponding domain model representing the status catalog.
     */
    public static StatusCatalog mapToModel(StatusCatalogSchema statusCatalogSchema) {
        return new StatusCatalog(
                statusCatalogSchema.getId(),
                statusCatalogSchema.getType()
        );
    }
 /**
     * Maps a {@link StatusCatalog} domain model to a {@link StatusCatalogSchema} database schema.
     *
     * @param statusCatalog The domain model representing a status catalog.
     * @return A corresponding database schema representing the status catalog.
     */
    public static StatusCatalogSchema mapToSchema(StatusCatalog statusCatalog) {
        return new StatusCatalogSchema(
                statusCatalog.id(),
                statusCatalog.type()
        );
    }
}
