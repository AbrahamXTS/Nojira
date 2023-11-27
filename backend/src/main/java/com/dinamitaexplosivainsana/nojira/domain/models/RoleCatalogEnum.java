package com.dinamitaexplosivainsana.nojira.domain.models;

import lombok.Getter;

@Getter
public enum RoleCatalogEnum {

    OWNER(1, "Dueño"),
    INVITED(2, "Invitado");

    private final Integer id;
    private final String type;

    RoleCatalogEnum(Integer id, String type) {
        this.id = id;
        this.type = type;
    }
}