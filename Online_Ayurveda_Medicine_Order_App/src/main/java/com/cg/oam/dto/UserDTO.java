package com.cg.oam.dto;

import javax.validation.constraints.NotNull;

public class UserDTO {
	
	int userId;
	
	@NotNull(message = "Please provide Username")
	String userName;
	
	@NotNull(message = "Please provide type of User")
	String userType;
	
	public UserDTO() {}
	
	public UserDTO(int userId, String userName, String userType) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userType = userType;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userName=" + userName + ", userType=" + userType + "]";
	}
	
	

}
