package com.dinamitaexplosivainsana.nojira.schemas;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "task")
public class TaskSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "estimated", nullable = false)
    private Time estimated;

    @Column(name = "total", nullable = false)
    private Time total;

    @ManyToOne
    @JoinColumn( nullable = false)
    private ProjectSchema project;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserSchema user;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToOne
    @JoinColumn(nullable = false)
    private StatusCatalogSchema status;
}
