package com.dinamitaexplosivainsana.nojira.infrastructure.initializers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dinamitaexplosivainsana.nojira.domain.models.RoleCatalog;
import com.dinamitaexplosivainsana.nojira.domain.models.RoleCatalogEnum;
import com.dinamitaexplosivainsana.nojira.infrastructure.repositories.RoleCatalogRepositoryImpl;

@Component
public class RoleCatalogInitializer implements CommandLineRunner {
    private final RoleCatalogRepositoryImpl roleCatalogRepositoryImpl;

    public RoleCatalogInitializer(RoleCatalogRepositoryImpl roleCatalogRepositoryImpl) {
        this.roleCatalogRepositoryImpl = roleCatalogRepositoryImpl;
    }

    @Override
    public void run(String... args) {
        RoleCatalog ownerRole = new RoleCatalog(RoleCatalogEnum.OWNER.getId(), RoleCatalogEnum.OWNER.getType());
        RoleCatalog invitedRole = new RoleCatalog(RoleCatalogEnum.INVITED.getId(), RoleCatalogEnum.INVITED.getType());

        roleCatalogRepositoryImpl.saveRoleCatalog(ownerRole);
        roleCatalogRepositoryImpl.saveRoleCatalog(invitedRole);
    }
}
