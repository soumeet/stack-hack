package com.hackerearth.stackhack.tasksapi.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class AddTaskDTO {
	
	private String taskName;
	private Date dueDate;
	private Integer labelCode;
	private Integer statusCode;
	private Integer userId;
	
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
	
	public Integer getLabelCode() {
		return labelCode;
	}

	public void setLabelCode(Integer labelCode) {
		this.labelCode = labelCode;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "{ taskName: " + taskName + ", dueDate: " + dueDate + ", labelCode: " + labelCode + ", statusCode: "
				+ statusCode + ", userId: " + userId+ " }";
	}
}