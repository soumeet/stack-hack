package com.hackerearth.stackhack.tasksapi.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class AddTaskDTO {
	
	private String taskName;
	private Date dueDate;
	
	public AddTaskDTO() {
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
}