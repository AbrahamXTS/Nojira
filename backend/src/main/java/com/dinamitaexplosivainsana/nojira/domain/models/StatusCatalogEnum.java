package com.dinamitaexplosivainsana.nojira.domain.models;

public enum StatusCatalogEnum {
    TO_DO(1, "Por hacer"),
    IN_PROGRESS(2,"En progreso"),
    FINALIZED(3,"Finalizado");

    private final Integer id;

    private final String type;
    StatusCatalogEnum(Integer id, String type) {
        this.type = type;
        this.id = id;
    }

    public String getType(){
        return this.type;
    }

    public Integer getId(){
        return this.id;
    }
}
