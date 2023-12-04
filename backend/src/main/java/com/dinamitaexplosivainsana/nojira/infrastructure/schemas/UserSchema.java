package com.dinamitaexplosivainsana.nojira.infrastructure.schemas;

import jakarta.persistence.*;
import lombok.*;
/**
 * Represents the JPA entity for users in the database.
 * Instances of this class are managed by JPA and stored in the "user" table.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
public class UserSchema {
    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    /**
     * The full name of the user.
     */
    @Column(nullable = false)
    private String fullName;
     /**
     * The email address of the user. It must be unique.
     */
    @Column(nullable = false, unique = true)
    private String email;
    /**
     * The password associated with the user.
     */
    @Column(nullable = false)
    private String password;
}