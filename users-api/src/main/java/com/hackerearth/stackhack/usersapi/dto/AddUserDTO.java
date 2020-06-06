package com.hackerearth.stackhack.usersapi.dto;

public class AddUserDTO {
	
	private String userName;
	private String password;
	
	public AddUserDTO() {
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

	@Override
	public String toString() {
		return "{ userName: " + userName + ", password: " + password + " }";
	}
}