package com.cybage.dto;

import javax.persistence.Column;

public class SportsDto {
	private Integer sportId;
	private String sportName;
	private Integer userId;
	public SportsDto() {
		super();
	}
	public SportsDto(Integer sportId, String sportName, Integer userId) {
		super();
		this.sportId = sportId;
		this.sportName = sportName;
		this.userId = userId;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "SportsDto [sportId=" + sportId + ", sportName=" + sportName + ", userId=" + userId + "]";
	}
	
}
