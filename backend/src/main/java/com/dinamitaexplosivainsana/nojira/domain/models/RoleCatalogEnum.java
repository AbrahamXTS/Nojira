package com.dinamitaexplosivainsana.nojira.domain.models;

public enum RoleCatalogEnum {

    OWNER(1, "Dueño"),
    INVITED(2, "Invitado");

    private final Integer id;
    private final String type;

    RoleCatalogEnum(Integer id, String type){
        this.id = id;
        this.type = type;
    }

    public Integer getId(){
        return this.id;
    }

    public String getType() {
        return type;
    }
}