package com.cybage.entity;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "batches")
public class Batches {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer batchId;
	@Column(nullable = false)
	private LocalTime startTime;
	@Column(nullable = false)
	private LocalTime endTime;
	@Column(nullable = false)
	private Integer batchSize;

	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
	@JoinColumn(name = "sport_id")
	private Sports sport;

	public Batches() {
		super();
	}

	public Batches(LocalTime startTime, LocalTime endTime, Integer batchSize, Sports sport) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.batchSize = batchSize;
		this.sport = sport;
	}
	
	public Batches(LocalTime startTime, LocalTime endTime, Integer batchSize) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.batchSize = batchSize;
	}
	
	public Batches(Integer batchId,LocalTime startTime, LocalTime endTime, Integer batchSize) {
		super();
		this.batchId=batchId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.batchSize = batchSize;
	}


	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public Integer getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(Integer batchSize) {
		this.batchSize = batchSize;
	}

	public Sports getSport() {
		return sport;
	}

	public void setSport(Sports sport) {
		this.sport = sport;
	}

	@Override
	public String toString() {
		return "Batches [batchId=" + batchId + ", startTime=" + startTime + ", endTime=" + endTime + ", batchSize="
				+ batchSize + ", sport=" + sport + "]";
	}
}
