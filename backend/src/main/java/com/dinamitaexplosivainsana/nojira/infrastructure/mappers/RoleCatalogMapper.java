package com.dinamitaexplosivainsana.nojira.infrastructure.mappers;

import com.dinamitaexplosivainsana.nojira.domain.models.RoleCatalog;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleCatalogSchema;

public class RoleCatalogMapper {
    private RoleCatalogMapper() {
    }

    public static RoleCatalog mapToModel(RoleCatalogSchema roleCatalogSchema) {
        return new RoleCatalog(
                roleCatalogSchema.getId(),
                roleCatalogSchema.getType()
        );
    }

    public static RoleCatalogSchema mapToSchema(RoleCatalog roleCatalog) {
        return new RoleCatalogSchema(
                roleCatalog.id(),
                roleCatalog.type()
        );
    }
}
