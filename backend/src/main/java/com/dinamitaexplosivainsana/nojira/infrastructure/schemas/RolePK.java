package com.dinamitaexplosivainsana.nojira.infrastructure.schemas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RolePK implements Serializable {
    private UserSchema user;
    private ProjectSchema project;
    private RoleCatalogSchema roleCatalog;
}