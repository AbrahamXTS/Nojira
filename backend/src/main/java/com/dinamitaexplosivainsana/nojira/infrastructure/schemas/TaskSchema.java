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

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "estimated", nullable = false)
	private Integer timeEstimatedInMinutes;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "total", nullable = false)
	private Integer timeUsedInMinutes;

	@ManyToOne
	@JoinColumn(nullable = false)
	private ProjectSchema project;

	@ManyToOne
	@JoinColumn(nullable = false)
	private UserSchema user;

	@OneToOne
	@JoinColumn(nullable = false)
	private StatusCatalogSchema status;

	@PrePersist
	public void prePersist(){
		if(Objects.isNull(status)){
			this.status = StatusCatalogSchema
					.builder()
					.id(StatusCatalogEnum.TO_DO.getId())
					.type(StatusCatalogEnum.TO_DO.getType())
					.build();
		}
	}

}
