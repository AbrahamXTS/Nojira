package com.dinamitaexplosivainsana.nojira.domain.dto;
/**
 * Owner of a project.
 *
 * @param ownerId        Unique identifier of the owner.
 * @param ownerFullName  Full name of the owner.
 */
public record OwnerDTO(String ownerId, String ownerFullName) {}
