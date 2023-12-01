package com.dinamitaexplosivainsana.nojira.infrastructure.initializers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dinamitaexplosivainsana.nojira.domain.models.StatusCatalog;
import com.dinamitaexplosivainsana.nojira.domain.models.StatusCatalogEnum;
import com.dinamitaexplosivainsana.nojira.infrastructure.repositories.StatusCatalogRepositoryImpl;

@Component
public class StatusCatalogInitializer implements CommandLineRunner {
    private final StatusCatalogRepositoryImpl statusRepositoryImpl;

    public StatusCatalogInitializer(StatusCatalogRepositoryImpl statusRepositoryImpl) {
        this.statusRepositoryImpl = statusRepositoryImpl;
    }

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
