package com.hackerearth.stackhack.usersapi.dto;

import java.sql.Timestamp;

public class UserDTO {

	private Integer userId;
	private String userName;
	private String password;
	private Timestamp tCreated;
	private Timestamp tUpdated;

	public UserDTO() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return "{ userId: " + userId + ", userName: " + userName + "password: " + password +" }";
	}
}