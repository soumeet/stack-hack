package com.hackerearth.stackhack.tasksapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hackerearth.stackhack.tasksapi.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	@Query("SELECT t FROM Task t where t.userId=?1")
	public List<Task> getTasksByUserId(Integer userId);
}
