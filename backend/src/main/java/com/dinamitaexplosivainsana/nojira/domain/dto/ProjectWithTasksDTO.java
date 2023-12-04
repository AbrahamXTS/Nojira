package com.dinamitaexplosivainsana.nojira.domain.dto;

import java.util.List;
/**
 * Represents project information along with its associated tasks.
 *
 * @param projectId   Unique identifier of the project.
 * @param projectName Name of the project.
 * @param tasks       List of tasks associated with the project, represented by {@code TaskDTO}.
 */
public record ProjectWithTasksDTO(String projectId, String projectName, List<TaskDTO> tasks) {
}
