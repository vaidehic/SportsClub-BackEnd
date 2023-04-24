package com.cybage.dto;

import javax.persistence.Column;

import com.cybage.entity.Roles;

public class ManagerDto {
	private Integer userId;
	private String name;
	private String email;
	private String phoneNumber;
	private Roles role;
	private Integer sportId;
	private String sportName;
	public ManagerDto() {
		super();
	}
	public ManagerDto(Integer userId, String name, String email, String phoneNumber, Roles role, Integer sportId,
			String sportName) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.sportId = sportId;
		this.sportName = sportName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	public Integer getSportId() {
		return sportId;
	}
	public void setSportId(Integer sportId) {
		this.sportId = sportId;
	}
	public String getSportName() {
		return sportName;
	}
	public void setSportName(String sportName) {
		this.sportName = sportName;
	}
	@Override
	public String toString() {
		return "ManagerDto [userId=" + userId + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", role=" + role + ", sportId=" + sportId + ", sportName=" + sportName + "]";
	}
	
}
