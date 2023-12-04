package com.dinamitaexplosivainsana.nojira.domain.models;
/**
 * Represents a task within the system.
 *
 * <p>
 * A task contains information such as:
 * - {@code id}: Unique identifier for the task.
 * - {@code description}: Description or details of the task.
 * - {@code timeEstimatedInMinutes}: Estimated time required to complete the task in minutes.
 * - {@code title}: Title or name of the task.
 * - {@code timeUsedInMinutes}: Time actually spent on the task in minutes.
 * - {@code project}: Project to which the task belongs.
 * - {@code user}: User assigned to the task.
 * - {@code statusCatalog}: Status of the task, represented by a {@code StatusCatalog} object.
 * </p>
 */
public record Task(
        String id,
        String description,
        Integer timeEstimatedInMinutes,
        String title,
        Integer timeUsedInMinutes,
        Project project,
        User user,
        StatusCatalog statusCatalog
) {
}
