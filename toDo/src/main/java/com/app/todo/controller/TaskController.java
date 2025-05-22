package com.app.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.app.todo.models.Task;
import com.app.todo.service.TaskService;
import com.app.todo.service.TaskSummaryService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class TaskController {

	@Autowired
	private  TaskService taskService;
	
	@Autowired
	private TaskSummaryService taskSummaryService;
	
	@GetMapping("/todos")
	public List<Task> getTasks() {
		List<Task> tasks=taskService.getAllTasks();
		return tasks;
	}
	@PostMapping("/todos")
	public Task createTasks(@RequestBody Task task) {
		
		return taskService.createTask(task);
	}
	@PutMapping("/todos/{id}")
	public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
	    Task task = taskService.getTaskById(id); 
	    task.setTitle(updatedTask.getTitle());
	    task.setDescription(updatedTask.getDescription());
	    task.setCompleted(updatedTask.isCompleted());
	    return taskService.createTask(task); 
	}
	@DeleteMapping("/todos/{id}")
	public void deleteTask(@PathVariable Long id) {
	    taskService.deleteTask(id);
	}
	
	@PostMapping("/summarize")
	public ResponseEntity<String> summarizeTasks() {
	    
	    	List<Task> tasks = taskService.getAllTasks();
	    
	    	String summary = taskSummaryService.summarizeTasks(tasks);
	        return ResponseEntity.ok(summary);
	    
	}
}
