package com.dinamitaexplosivainsana.nojira.domain.models;

public record Role(
        User user,
        Project project,
        RoleCatalog roleCatalog
) {
}
