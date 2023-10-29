package com.dinamitaexplosivainsana.nojira.infrastructure.schemas;

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
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "estimated", nullable = false)
	private Time estimated;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "total", nullable = false)
	private Time total;

	@ManyToOne
	@JoinColumn(nullable = false)
	private ProjectSchema project;

	@ManyToOne
	@JoinColumn(nullable = false)
	private UserSchema user;

	@OneToOne
	@JoinColumn(nullable = false)
	private StatusCatalogSchema status;
}
