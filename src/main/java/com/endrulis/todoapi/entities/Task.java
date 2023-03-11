package com.endrulis.todoapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.endrulis.todoapi.model.TaskStatus;

@Table(name = "tasks")
@Entity
public class Task {
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
	@SequenceGenerator(name = "task_sequence", sequenceName = "task_sequence", allocationSize = 1)
	private Long id;
	@Column(nullable = false)
	@NotBlank(message = "Name is required")
	private String name;
	@Column(nullable = true)
	private String description;
	@Column(nullable = false)
	@NotBlank(message = "Due date is required")
	private String dueDate;
	@Column(nullable = false)
	@NotNull(message = "Status is required")
	@Enumerated(EnumType.STRING)
	private TaskStatus status;

	public Task() {

	}

	public Task(String name, String description, String dueDate, TaskStatus status) {
		this.name = name;
		this.description = description;
		this.dueDate = dueDate;
		this.status = status;
	}

	public Task(Long id, String name, String description, String dueDate, TaskStatus status) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.dueDate = dueDate;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

}
