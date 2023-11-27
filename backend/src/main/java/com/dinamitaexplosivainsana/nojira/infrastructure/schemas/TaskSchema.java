package com.dinamitaexplosivainsana.nojira.infrastructure.schemas;

import com.dinamitaexplosivainsana.nojira.domain.models.StatusCatalogEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "task")
public class TaskSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String description;

    @Builder.Default
    @Column(nullable = false)
    private Integer timeEstimatedInMinutes = 0;

    @Column(nullable = false)
    private String title;

    @Builder.Default
    @Column(nullable = false)
    private Integer timeUsedInMinutes = 0;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ProjectSchema project;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserSchema user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private StatusCatalogSchema status;
}
