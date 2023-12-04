package com.dinamitaexplosivainsana.nojira.domain.models;

import lombok.Getter;

/**
 * Enumeration representing different statuses in the system for tasks.
 *
 * <p>
 * The available statuses include:
 * - {@code TO_DO}: Represents a task that is yet to be started.
 * - {@code IN_PROGRESS}: Represents a task that is currently being worked on.
 * - {@code FINALIZED}: Represents a task that has been completed.
 * </p>
 */
@Getter
public enum StatusCatalogEnum {
    TO_DO(1, "Por hacer"),
    IN_PROGRESS(2, "En progreso"),
    FINALIZED(3, "Finalizada");

    private final Integer id;
    private final String type;
 /**
     * Constructs a new {@code StatusCatalogEnum}.
     *
     * @param id   The unique identifier for the status.
     * @param type The human-readable type of the status.
     */
    StatusCatalogEnum(Integer id, String type) {
        this.type = type;
        this.id = id;
    }
}