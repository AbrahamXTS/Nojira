package com.dinamitaexplosivainsana.nojira.domain.dto;
/**
 * Data Transfer Object (DTO) representing information about a task.
 *
 * @param taskId      Unique identifier of the task.
 * @param title       Title of the task.
 * @param description Description of the task.
 * @param status      Status of the task.
 * @param times       DTO containing information about the estimated and used time for the task.
 * @param assignedTo  DTO containing information about the owner of the task.
 */
public record TaskDTO(
        String taskId,
        String title,
        String description,
        String status,
        TimesDTO times,
        OwnerDTO assignedTo
) {
}
