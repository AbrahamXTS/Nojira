package com.dinamitaexplosivainsana.nojira.infrastructure.mappers;

import com.dinamitaexplosivainsana.nojira.domain.models.StatusCatalog;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.StatusCatalogSchema;

public class StatusCatalogMapper {
    private StatusCatalogMapper() {
    }

    public static StatusCatalog mapToModel(StatusCatalogSchema statusCatalogSchema) {
        return new StatusCatalog(
                statusCatalogSchema.getId(),
                statusCatalogSchema.getType()
        );
    }

    public static StatusCatalogSchema mapToSchema(StatusCatalog statusCatalog) {
        return new StatusCatalogSchema(
                statusCatalog.id(),
                statusCatalog.type()
        );
    }
}
