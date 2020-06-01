package com.hackerearth.stackhack.tasksapi.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class TaskDTO {

	private Integer taskId;
	private String taskName;
	private Date dueDate;
	private Timestamp tCreated;
	private Timestamp tUpdated;
	
	public TaskDTO() {
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
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

	public Timestamp gettCreated() {
		return tCreated;
	}

	public void settCreated(Timestamp tCreated) {
		this.tCreated = tCreated;
	}

	public Timestamp gettUpdated() {
		return tUpdated;
	}

	public void settUpdated(Timestamp tUpdated) {
		this.tUpdated = tUpdated;
	}
	
	@Override
	public String toString() {
		return "{ taskId: " + taskId + ", taskName: " + taskName + ", dueDate: " + dueDate + " }";
	}
}