package com.hackerearth.stackhack.tasksapi.dto;

public class UpdateTaskDTO {
	private Integer taskId;
	private Integer statusCode;
	
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	@Override
	public String toString() {
		return "{ taskId: " + taskId + ", statusCode: " + statusCode + " }";
	}
}
