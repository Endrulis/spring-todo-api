package com.endrulis.todoapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.endrulis.todoapi.entities.Task;
import com.endrulis.todoapi.model.TaskStatus;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	Optional<Task> findByName(String name);

	Optional<Task> findByDescription(String description);

	Optional<Task> findByDueDate(String dueDate);

	Optional<Task> findByStatus(TaskStatus status);
}
