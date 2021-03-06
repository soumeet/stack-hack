package com.hackerearth.stackhack.tasksapi.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class TaskDTO {

	private Integer taskId;
	private String taskName;
	private Date dueDate;
	private Integer labelCode;
	private Integer statusCode;
	private Timestamp tCreated;
	private Timestamp tUpdated;
	private Integer userId;

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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "{ taskId: " + taskId + ", taskName: " + taskName + ", dueDate: " + dueDate + ", labelCode: " + labelCode
				+ ", statusCode: " + statusCode + ", userId: " + userId + " }";
	}
}