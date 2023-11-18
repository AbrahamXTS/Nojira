package com.dinamitaexplosivainsana.nojira.domain.dto;

public record SuccessfulUpdateTaskDTO(String taskId, String title, String description, String status, TaskTimesDTO times, TaskAsignedDTO asignedTo) {
    
}
