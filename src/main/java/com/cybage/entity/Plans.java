package com.cybage.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Table(name = "plans")
public class Plans {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer planId;
	@Column(nullable = false)
	private String planName;
	@Column(nullable = false)
	private LocalDate planStartDate;
	@Column(nullable = false)
	private LocalDate planEndDate;
	@Column(nullable = false)
	private String planDuration;
	@Column(nullable = false)
	private Double planFees;
	private Double totalLikes;
	private Double totalDisLikes;
	
	//@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	//@JoinColumn(name = "plan_id",referencedColumnName = "planId")
	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Comments> comments;
	
	@ManyToOne
	(fetch = FetchType.LAZY)
	@JoinColumn(name = "sport_id")
	@JsonIgnore
	private Sports sport;

	public Plans() {
		super();
	}

	public Plans(String planName, LocalDate planStartDate, LocalDate planEndDate, String planDuration, Double planFees,
			Double totalLikes, Double totalDisLikes, List<Comments> comments, Sports sport) {
		super();
		this.planName = planName;
		this.planStartDate = planStartDate;
		this.planEndDate = planEndDate;
		this.planDuration = planDuration;
		this.planFees = planFees;
		this.totalLikes = totalLikes;
		this.totalDisLikes = totalDisLikes;
		this.comments = comments;
		this.sport = sport;
	}
//	
//	public Plans(Integer id,String planName, LocalDate planStartDate, LocalDate planEndDate, 
//			String planDuration, Double planFees) {
//		super();
//		this.planId=id;
//		this.planName = planName;
//		this.planStartDate = planStartDate;
//		this.planEndDate = planEndDate;
//		this.planDuration = planDuration;
//		this.planFees = planFees;
//		
//		}

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

	public Double getTotalLikes() {
		return totalLikes;
	}

	public void setTotalLikes(Double totalLikes) {
		this.totalLikes = totalLikes;
	}

	public Double getTotalDisLikes() {
		return totalDisLikes;
	}

	public void setTotalDisLikes(Double totalDisLikes) {
		this.totalDisLikes = totalDisLikes;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public Sports getSport() {
		return sport;
	}

	public void setSport(Sports sport) {
		this.sport = sport;
	}

	@Override
	public String toString() {
		return "Plans [planId=" + planId + ", planName=" + planName + ", planStartDate=" + planStartDate
				+ ", planEndDate=" + planEndDate + ", planDuration=" + planDuration + ", planFees=" + planFees
				+ ", totalLikes=" + totalLikes + ", totalDisLikes=" + totalDisLikes + ", comments=" + comments + "]";
	}	
}


