package com.dinamitaexplosivainsana.nojira.repositories;

import com.dinamitaexplosivainsana.nojira.schemas.TaskSchema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskSchema, String> {
}
