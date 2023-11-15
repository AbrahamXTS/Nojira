package com.dinamitaexplosivainsana.nojira.domain.dto;

public record TaskDTO(String taskId, String title, String description, StatusDTO status, TimesDTO times, AssignedDTO assigned) {
}
