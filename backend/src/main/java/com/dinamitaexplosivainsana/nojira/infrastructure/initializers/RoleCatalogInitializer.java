package com.dinamitaexplosivainsana.nojira.infrastructure.initializers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dinamitaexplosivainsana.nojira.domain.models.RoleCatalog;
import com.dinamitaexplosivainsana.nojira.domain.models.RoleCatalogEnum;
import com.dinamitaexplosivainsana.nojira.infrastructure.repositories.RoleCatalogRepositoryImpl;
/**
 * Component class implementing {@code CommandLineRunner} to initialize role catalog data
 * when the Spring Boot application starts.
 *
 * @Component Indicates that this class is a Spring component.
 * @CommandLineRunner Interface used to indicate that a bean should run when it is contained
 *                     within a {@code SpringApplication}.
 */
@Component
public class RoleCatalogInitializer implements CommandLineRunner {
    private final RoleCatalogRepositoryImpl roleCatalogRepositoryImpl;
 /**
     * Constructs a {@code RoleCatalogInitializer} instance.
     *
     * @param roleCatalogRepositoryImpl The repository responsible for role catalog operations.
     */
    public RoleCatalogInitializer(RoleCatalogRepositoryImpl roleCatalogRepositoryImpl) {
        this.roleCatalogRepositoryImpl = roleCatalogRepositoryImpl;
    }
/**
     * Initializes role catalog data during the application startup.
     *
     * @param args The command-line arguments.
     */
    @Override
    public void run(String... args) {
        RoleCatalog ownerRole = new RoleCatalog(RoleCatalogEnum.OWNER.getId(), RoleCatalogEnum.OWNER.getType());
        RoleCatalog invitedRole = new RoleCatalog(RoleCatalogEnum.INVITED.getId(), RoleCatalogEnum.INVITED.getType());

        roleCatalogRepositoryImpl.saveRoleCatalog(ownerRole);
        roleCatalogRepositoryImpl.saveRoleCatalog(invitedRole);
    }
}
