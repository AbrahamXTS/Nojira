package com.dinamitaexplosivainsana.nojira.domain.models;

import lombok.Getter;
/**
 * Enumeration representing the possible role catalog entries.
 */
@Getter
public enum RoleCatalogEnum {

    OWNER(1, "Dueño"),
    INVITED(2, "Invitado");

    private final Integer id;
    private final String type;
     /**
     * Constructs a RoleCatalogEnum entry with the specified id and type.
     *
     * @param id   The unique identifier of the role catalog entry.
     * @param type The type or name of the role.
     */
    RoleCatalogEnum(Integer id, String type) {
        this.id = id;
        this.type = type;
    }
}