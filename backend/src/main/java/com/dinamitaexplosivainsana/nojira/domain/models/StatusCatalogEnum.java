package com.dinamitaexplosivainsana.nojira.domain.models;

import lombok.Getter;

@Getter
public enum StatusCatalogEnum {
    TO_DO(1, "Por hacer"),
    IN_PROGRESS(2, "En progreso"),
    FINALIZED(3, "Finalizada");

    private final Integer id;
    private final String type;

    StatusCatalogEnum(Integer id, String type) {
        this.type = type;
        this.id = id;
    }
}