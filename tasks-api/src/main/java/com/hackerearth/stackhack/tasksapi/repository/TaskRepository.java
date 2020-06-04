package com.hackerearth.stackhack.tasksapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackerearth.stackhack.tasksapi.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	
}
