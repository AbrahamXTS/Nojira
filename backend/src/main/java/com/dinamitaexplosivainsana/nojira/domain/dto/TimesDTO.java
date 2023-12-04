package com.dinamitaexplosivainsana.nojira.domain.dto;
/**
 * Data Transfer Object (DTO) representing information about the estimated and used time for a task.
 *
 * @param estimated Estimated time for the task.
 * @param used      Used time for the task.
 */
public record TimesDTO(Integer estimated, Integer used) {
}
