package com.cybage.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Enrollments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enrollmentId;
	@Column(nullable = false)
	private Double amountPaid;
	@Column(nullable = false)
	private LocalDate planStartDate;
	@Column(nullable = false)
	private LocalDate planEndDate;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EnrollmentStatus enrollmentStatus;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id",nullable = false)
	private Users user;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "sport_id",nullable = false)
	private Sports sport;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "plan_id",nullable = false)
	private Plans plan;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "batch_id",nullable = true)
	private Batches batch;
	
//	@ElementCollection
//	@CollectionTable(name = "rejectedReason",joinColumns = @JoinColumn(name = "enroll_id"))
//	private List<String> reason;

	public Enrollments() {
		super();
	}

	public Enrollments(Double amountPaid, EnrollmentStatus enrollmentStatus, Users user, Sports sport, Plans plan,
			Batches batch) {
		super();
		this.amountPaid = amountPaid;
		this.enrollmentStatus = enrollmentStatus;
		this.user = user;
		this.sport = sport;
		this.plan = plan;
		this.batch = batch;
	}
	
	public Enrollments(Integer enrollmentId,Double amountPaid, EnrollmentStatus enrollmentStatus) {
		super();
		this.enrollmentId=enrollmentId;
		this.amountPaid = amountPaid;
		this.enrollmentStatus = enrollmentStatus;
	
	}

	public Integer getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(Integer enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public EnrollmentStatus getEnrollmentStatus() {
		return enrollmentStatus;
	}

	public void setEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Sports getSport() {
		return sport;
	}

	public void setSport(Sports sport) {
		this.sport = sport;
	}

	public Plans getPlan() {
		return plan;
	}

	public void setPlan(Plans plan) {
		this.plan = plan;
	}

	public Batches getBatch() {
		return batch;
	}

	public void setBatch(Batches batch) {
		this.batch = batch;
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

	@Override
	public String toString() {
		return "Enrollments [enrollmentId=" + enrollmentId + ", amountPaid=" + amountPaid + ", enrollmentStatus="
				+ enrollmentStatus + ", user=" + user + ", sport=" + sport + ", plan=" + plan + ", batch=" + batch
				+ "]";
	}
}
