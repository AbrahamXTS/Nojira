package com.dinamitaexplosivainsana.nojira.domain.dto;

import java.util.List;

public record ProjectWithTasksDTO(String projectId, String projectName, List<TaskDTO> tasks) {
}
