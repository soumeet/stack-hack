package com.hackerearth.stackhack.usersapi.dto;

public class UserResponseDTO {

	private Integer userId;
	private String userName;

	public UserResponseDTO() {
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

	@Override
	public String toString() {
		return "{ userId: " + userId + ", userName: " + userName +" }";
	}
}