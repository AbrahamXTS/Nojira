package com.dinamitaexplosivainsana.nojira.domain.models;

public record Status(Integer id, String type) {
    public Status(){
        this(1, "Por hacer");
    }
}
