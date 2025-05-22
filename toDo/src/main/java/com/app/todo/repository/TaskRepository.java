package com.app.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.todo.models.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {

}
