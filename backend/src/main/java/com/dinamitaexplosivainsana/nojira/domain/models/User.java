package com.dinamitaexplosivainsana.nojira.domain.models;
/**
 * Represents a user within the system.
 *
 * <p>
 * A user contains information such as:
 * - {@code id}: Unique identifier for the user.
 * - {@code fullName}: Full name of the user.
 * - {@code email}: Email address of the user.
 * - {@code password}: Password associated with the user's account.
 * </p>
 */
public record User(String id, String fullName, String email, String password) {
}
