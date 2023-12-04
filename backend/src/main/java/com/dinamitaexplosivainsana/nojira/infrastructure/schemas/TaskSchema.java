package com.dinamitaexplosivainsana.nojira.infrastructure.schemas;

import com.dinamitaexplosivainsana.nojira.domain.models.StatusCatalogEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
/**
 * Represents the JPA entity for tasks in the database.
 * Instances of this class are managed by JPA and stored in the "task" table.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "task")
public class TaskSchema {
    /**
     * The unique identifier for the task.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
     /**
     * The description of the task.
     */
    @Column
    private String description;
     /**
     * The estimated time for completing the task in minutes.
     * Default value is 0.
     */
    @Builder.Default
    @Column(nullable = false)
    private Integer timeEstimatedInMinutes = 0;
    /**
     * The title of the task.
     */
    @Column(nullable = false)
    private String title;
     /**
     * The actual time used to complete the task in minutes.
     * Default value is 0.
     */
    @Builder.Default
    @Column(nullable = false)
    private Integer timeUsedInMinutes = 0;
    /**
     * The project associated with the task.
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    private ProjectSchema project;
     /**
     * The user assigned to the task.
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    private UserSchema user;
    /**
     * The status of the task.
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    private StatusCatalogSchema status;
}
