package com.dinamitaexplosivainsana.nojira.domain.dto;

public record SuccessfulCreatedTaskDTO(String taskId, String title, String description, String status, TaskTimesDTO times, TaskAssignedToDTO asignedTo) {
}
