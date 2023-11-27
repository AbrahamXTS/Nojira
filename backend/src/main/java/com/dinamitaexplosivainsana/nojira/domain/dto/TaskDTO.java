package com.dinamitaexplosivainsana.nojira.domain.dto;

public record TaskDTO(
        String taskId,
        String title,
        String description,
        String status,
        TimesDTO times,
        OwnerDTO assignedTo
) {
}
