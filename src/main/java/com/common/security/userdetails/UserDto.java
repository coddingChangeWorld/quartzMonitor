package com.common.security.userdetails;

public class UserDto {
	private String username;
	private String userPhoto;
	private Double currency;

	public UserDto(String userPhoto, Double currency, String username) {
		this.userPhoto = userPhoto;
		this.currency = currency;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPhoto() {
		return this.userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public Double getMoney() {
		return this.currency;
	}

	public void setMoney(Double currency) {
		this.currency = currency;
	}
}
