package com.endrulis.todoapi.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endrulis.todoapi.entities.Task;
import com.endrulis.todoapi.exceptions.TaskNotFoundException;
import com.endrulis.todoapi.model.TaskStatus;
import com.endrulis.todoapi.repositories.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;

	public void createTask(@Valid Task task) {
		if (taskRepository.findByName(task.getName()).isPresent())
			throw new IllegalArgumentException("Task with name " + task.getName() + " already exists");
		try {
			taskRepository.save(task);
		} catch (Exception e) {
			throw new RuntimeException("Error creating user: " + e.getMessage());
		}
	}

	public void populateTask(Task task, Task taskDTO) {
		task.setName(taskDTO.getName());
		task.setDescription(taskDTO.getDescription());
		task.setDueDate(taskDTO.getDueDate());
		task.setStatus(taskDTO.getStatus());
	}

	public Task getTaskById(Long id) {
		return taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user's Id: " + id));
	}

	public Task getTaskByName(String name) {
		return taskRepository.findByName(name)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user's name: " + name));
	}

	public Task getTaskByDescription(String description) {
		return taskRepository.findByDescription(description)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user's description: " + description));
	}

	public Task getTaskByDueDate(String dueDate) {
		return taskRepository.findByDueDate(dueDate)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user's due date: " + dueDate));
	}

	public Task getTaskByStatus(TaskStatus status) {
		return taskRepository.findByStatus(status)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user's status: " + status));
	}

	public void updateTask(Long id, @Valid Task newTask) {
		taskRepository.findById(id).map(task -> {
			task.setName(newTask.getName());
			task.setDescription(newTask.getDescription());
			task.setDueDate(newTask.getDueDate());
			task.setStatus(newTask.getStatus());
			return taskRepository.save(task);
		}).orElseThrow(() -> new TaskNotFoundException("Task not found by id: " + id));
	}

	public void deleteById(@Valid Long id) {
		taskRepository.deleteById(id);
	}

	public void save(@Valid Task task) {
		taskRepository.save(task);
	}

	public List<Task> getAllTasks() {
		try {
			return taskRepository.findAll();
		} catch (Exception e) {
			throw new RuntimeException("Error retrieving users from the database: " + e.getMessage());
		}
	}
}
