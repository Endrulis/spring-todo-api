package com.endrulis.todoapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.endrulis.todoapi.entities.Task;
import com.endrulis.todoapi.exceptions.TaskNotFoundException;
import com.endrulis.todoapi.model.TaskStatus;
import com.endrulis.todoapi.services.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {
	@Autowired
	private TaskService taskService;

	@GetMapping("/add")
	public String addTaskForm(Model model) {
		model.addAttribute("task", new Task());
		return "add-task";
	}

	@PostMapping("/addTask")
	public String addTask(@Valid @ModelAttribute("task") Task taskDTO, BindingResult result) {
		Task task = new Task();
		taskService.populateTask(task, taskDTO);
		taskService.createTask(task);
		return "redirect:/tasks";
	}

	@GetMapping("/{id}")
	public Task getTaskById(@PathVariable Long id) {
		return taskService.getTaskById(id);
	}

	@GetMapping("/name/{name}")
	public Task getTaskByName(@PathVariable String name) {
		return taskService.getTaskByName(name);
	}

	@GetMapping("/description/{description}")
	public Task getTaskByDescription(@PathVariable String description) {
		return taskService.getTaskByDescription(description);
	}

	@GetMapping("/dueDate/{dueDate}")
	public Task getTaskByDueDate(@PathVariable String dueDate) {
		return taskService.getTaskByDueDate(dueDate);
	}

	@GetMapping("/status/{status}")
	public Task getTaskByStatus(@PathVariable TaskStatus status) {
		return taskService.getTaskByStatus(status);
	}

	@GetMapping
	public String getAllTasks(Model model) {
		List<Task> tasks = taskService.getAllTasks();
		model.addAttribute("tasks", tasks);
		return "tasks";
	}

	@GetMapping("/{id}/edit")
	public String showUpdateTaskForm(@PathVariable Long id, Model model) {
		Task task = taskService.getTaskById(id);
		model.addAttribute("task", task);
		return "update-task";
	}

	@PostMapping("/{id}")
	public String updateTask(@PathVariable Long id, @Valid @ModelAttribute("task") Task taskDTO, BindingResult result) {
		taskService.updateTask(id, taskDTO);
		return "redirect:/tasks";
	}

	@GetMapping("/delete/{id}")
	public String deleteTask(@PathVariable Long id, Model model) {
		try {
			taskService.deleteById(id);
			return "redirect:/tasks";
		} catch (TaskNotFoundException e) {
			throw new TaskNotFoundException("Task not found with id: " + id);
		}

	}
}
