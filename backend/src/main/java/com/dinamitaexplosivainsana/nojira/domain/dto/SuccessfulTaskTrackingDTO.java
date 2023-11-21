package com.dinamitaexplosivainsana.nojira.domain.dto;

public record SuccessfulTaskTrackingDTO(String taskId, String title, String description,
                                        String status, TaskTimesDTO times, AsignedToDTO AsignedTo) {
}
