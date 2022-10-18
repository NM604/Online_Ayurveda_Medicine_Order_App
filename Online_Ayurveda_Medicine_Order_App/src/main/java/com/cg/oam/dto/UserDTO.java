package com.cg.oam.dto;

import javax.validation.constraints.NotNull;

/**
 * The Class UserDTO.
 */
public class UserDTO {
	
	/** The user id. */
	int userId;
	
	/** The user name. */
	@NotNull(message = "Please provide Username")
	String userName;
	
	/** The user type. */
	@NotNull(message = "Please provide type of User")
	String userType;
	
	/**
	 * Instantiates a new user DTO.
	 */
	public UserDTO() {}
	
	/**
	 * Instantiates a new user DTO.
	 *
	 * @param userId the user id
	 * @param userName the user name
	 * @param userType the user type
	 */
	public UserDTO(int userId, String userName, String userType) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userType = userType;
	}
	
	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Gets the user type.
	 *
	 * @return the user type
	 */
	public String getUserType() {
		return userType;
	}
	
	/**
	 * Sets the user type.
	 *
	 * @param userType the new user type
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userName=" + userName + ", userType=" + userType + "]";
	}
	
	

}
