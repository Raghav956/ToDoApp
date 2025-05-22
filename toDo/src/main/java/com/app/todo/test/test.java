package com.app.todo.test;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;

import com.app.todo.controller.TaskController;
import com.app.todo.models.Task;
import com.app.todo.service.TaskService;
@SpringBootApplication(scanBasePackages = "com.app.todo")
public class test {

	
    
	public static void main(String args [])
	{
		ApplicationContext context = SpringApplication.run(test.class, args);

        TaskService taskService = context.getBean(TaskService.class);
        TaskController tc=context.getBean(TaskController.class);
		try {
		ResponseEntity<String> t=tc.summarizeTasks();
		System.out.println(t);}
		catch (Exception e){
			System.out.println("no records");
		}
		
	}
}
