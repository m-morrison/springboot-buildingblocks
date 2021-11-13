package com.stacksimplify.restservices.dtos;

public class UserMsDto {

	private Long userId;
	private String username;
	private String emailAddress;
	
	public UserMsDto() {
	}

	public UserMsDto(Long userId, String username, String emailAddress) {
		super();
		this.userId = userId;
		this.username = username;
		this.emailAddress = emailAddress;
	}

	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	
}
