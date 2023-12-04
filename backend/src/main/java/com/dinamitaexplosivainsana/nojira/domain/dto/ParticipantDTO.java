package com.dinamitaexplosivainsana.nojira.domain.dto;
/**
 * Represents a participant in a project.
 *
 * @param userId   Unique identifier of the participant.
 * @param fullName Full name of the participant.
 * @param email    Email of the participant.
 */
public record ParticipantDTO(String userId, String fullName, String email) {
}
