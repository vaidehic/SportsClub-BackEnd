package com.cybage.dto;

import java.time.LocalDate;

public class PlansDto {
	private Integer planId;
	private String planName;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private String planDuration;
	private Double planFees;
	private Integer sportId;
	private String sportName;
	public PlansDto() {
		super();
	}

public PlansDto(Integer planId, String planName, LocalDate planStartDate, LocalDate planEndDate,
			String planDuration, Double planFees, Integer sportId, String sportName) {
		super();
		this.planId = planId;
		this.planName = planName;
		this.planStartDate = planStartDate;
		this.planEndDate = planEndDate;
		this.planDuration = planDuration;
		this.planFees = planFees;
		this.sportId = sportId;
		this.sportName = sportName;
	}



	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public LocalDate getPlanStartDate() {
		return planStartDate;
	}
	public void setPlanStartDate(LocalDate planStartDate) {
		this.planStartDate = planStartDate;
	}
	public LocalDate getPlanEndDate() {
		return planEndDate;
	}
	public void setPlanEndDate(LocalDate planEndDate) {
		this.planEndDate = planEndDate;
	}
	public String getPlanDuration() {
		return planDuration;
	}
	public void setPlanDuration(String planDuration) {
		this.planDuration = planDuration;
	}
	public Double getPlanFees() {
		return planFees;
	}
	public void setPlanFees(Double planFees) {
		this.planFees = planFees;
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
	//tostring

	@Override
	public String toString() {
		return "PlansDto [planId=" + planId + ", planName=" + planName + ", planStartDate=" + planStartDate
				+ ", planEndDate=" + planEndDate + ", planDuration=" + planDuration + ", planFees=" + planFees
				+ ", sportId=" + sportId + ", sportName=" + sportName + "]";
	}
	
	
}
