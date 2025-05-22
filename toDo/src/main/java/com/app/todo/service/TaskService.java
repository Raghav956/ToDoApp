package com.app.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.todo.models.Task;
import com.app.todo.repository.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private  TaskRepository taskRepository;
	
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	public Task createTask(Task task) {
	    return taskRepository.save(task);
	}

	public Task getTaskById(Long id) {
		return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
		
	}

	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
		
	}

}
