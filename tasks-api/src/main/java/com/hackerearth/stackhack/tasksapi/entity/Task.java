package com.hackerearth.stackhack.tasksapi.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * The persistent class for the label database table.
 * 
 */
@Entity
@NamedQuery(name="Task.findAll", query="SELECT t FROM Task t")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="task_id")
	private Integer taskId;

	@Column(name="task_name")
	private String taskName;
	
	@Column(name="due_date")
	private Date dueDate;
	
	@Column(name="t_created", updatable=false)
	@CreationTimestamp
	private Timestamp tCreated;
	
	@Column(name="t_updated")
	@UpdateTimestamp
	private Timestamp tUpdated;

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
}