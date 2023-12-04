package com.dinamitaexplosivainsana.nojira.infrastructure.initializers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dinamitaexplosivainsana.nojira.domain.models.StatusCatalog;
import com.dinamitaexplosivainsana.nojira.domain.models.StatusCatalogEnum;
import com.dinamitaexplosivainsana.nojira.infrastructure.repositories.StatusCatalogRepositoryImpl;
/**
 * Component class implementing {@code CommandLineRunner} to initialize status catalog data
 * when the Spring Boot application starts.
 *
 * @Component Indicates that this class is a Spring component.
 * @CommandLineRunner Interface used to indicate that a bean should run when it is contained
 *                     within a {@code SpringApplication}.
 */
@Component
public class StatusCatalogInitializer implements CommandLineRunner {
    private final StatusCatalogRepositoryImpl statusRepositoryImpl;
/**
     * Constructs a {@code StatusCatalogInitializer} instance.
     *
     * @param statusRepositoryImpl The repository responsible for status catalog operations.
     */
    public StatusCatalogInitializer(StatusCatalogRepositoryImpl statusRepositoryImpl) {
        this.statusRepositoryImpl = statusRepositoryImpl;
    }
/**
     * Initializes status catalog data during the application startup.
     *
     * @param args The command-line arguments.
     */
    @Override
    public void run(String... args) {
        StatusCatalog toDoStatusCatalog = new StatusCatalog(StatusCatalogEnum.TO_DO.getId(), StatusCatalogEnum.TO_DO.getType());
        StatusCatalog inProgressStatusCatalog = new StatusCatalog(StatusCatalogEnum.IN_PROGRESS.getId(), StatusCatalogEnum.IN_PROGRESS.getType());
        StatusCatalog finalizedStatusCatalog = new StatusCatalog(StatusCatalogEnum.FINALIZED.getId(), StatusCatalogEnum.FINALIZED.getType());

        this.statusRepositoryImpl.saveStatus(toDoStatusCatalog);
        this.statusRepositoryImpl.saveStatus(inProgressStatusCatalog);
        this.statusRepositoryImpl.saveStatus(finalizedStatusCatalog);
    }
}
