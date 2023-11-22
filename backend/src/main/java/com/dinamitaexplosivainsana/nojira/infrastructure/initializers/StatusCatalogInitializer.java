package com.dinamitaexplosivainsana.nojira.infrastructure.initializers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dinamitaexplosivainsana.nojira.domain.models.Status;
import com.dinamitaexplosivainsana.nojira.domain.models.StatusCatalogEnum;
import com.dinamitaexplosivainsana.nojira.infrastructure.repositories.StatusRepositoryImpl;

@Component
public class StatusCatalogInitializer implements CommandLineRunner {
    private final StatusRepositoryImpl statusRepositoryImpl;

    public StatusCatalogInitializer(StatusRepositoryImpl statusRepositoryImpl) {
        this.statusRepositoryImpl = statusRepositoryImpl; 
    }

    @Override
    public void run(String... args) throws Exception {
        Status toDoStatus = new Status(StatusCatalogEnum.TO_DO.getId(), StatusCatalogEnum.TO_DO.getType());
        Status inProgressStatus = new Status(StatusCatalogEnum.IN_PROGRESS.getId(), StatusCatalogEnum.IN_PROGRESS.getType());
        Status finalizedStatus = new Status(StatusCatalogEnum.FINALIZED.getId(), StatusCatalogEnum.FINALIZED.getType());

        this.statusRepositoryImpl.saveStatus(toDoStatus);
        this.statusRepositoryImpl.saveStatus(inProgressStatus);
        this.statusRepositoryImpl.saveStatus(finalizedStatus);
    }    
}
