package com.dinamitaexplosivainsana.nojira.domain.models;
/**
 * Represents the role of a user in a project.
 *
 * @param user        The user associated with the role.
 * @param project     The project associated with the role.
 * @param roleCatalog The role catalog defining the role type.
 */
public record Role(
        User user,
        Project project,
        RoleCatalog roleCatalog
) {
}
