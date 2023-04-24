package com.cybage.dto;

public class PlanLikeDto {
	private Integer likeId;
	private boolean likeStatus;
	private int userId;
	private int planId;
	public PlanLikeDto() {
		super();
	}
	public PlanLikeDto(Integer likeId, boolean likeStatus, int userId, int planId) {
		super();
		this.likeId = likeId;
		this.likeStatus = likeStatus;
		this.userId = userId;
		this.planId = planId;
	}
	public PlanLikeDto(boolean likeStatus, int userId, int planId) {
		super();
		this.likeStatus = likeStatus;
		this.userId = userId;
		this.planId = planId;
	}
	public Integer getLikeId() {
		return likeId;
	}
	public void setLikeId(Integer likeId) {
		this.likeId = likeId;
	}
	public boolean isLikeStatus() {
		return likeStatus;
	}
	public void setLikeStatus(boolean likeStatus) {
		this.likeStatus = likeStatus;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	@Override
	public String toString() {
		return "PlanLikeDto [likeId=" + likeId + ", likeStatus=" + likeStatus + ", userId=" + userId + ", planId="
				+ planId + "]";
	}
}
