package com.cybage.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;
	private String comment;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Users user;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	//@ManyToOne(optional = false)
	@JoinColumn(name = "plan_id")
	@JsonIgnore
	private Plans plan;
	
	private LocalDateTime commentTime;
	
	public Comments(String comment, Users user, Plans plan, LocalDateTime commentTime) {
		super();
		this.comment = comment;
		this.user = user;
		this.plan = plan;
		this.commentTime = commentTime;
	}
	
	public Comments(Integer id,String comment,LocalDateTime commentTime) {
		super();
		this.commentId=id;
		this.comment = comment;
		this.commentTime = commentTime;
	}

	public Comments(String comment) {
		super();
		this.comment = comment;
	}

	public Comments() {
		super();		
	}
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}

	public LocalDateTime getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(LocalDateTime commentTime) {
		this.commentTime = commentTime;
	}

	public Plans getPlan() {
		return plan;
	}

	public void setPlan(Plans plan) {
		this.plan = plan;
	}

	@Override
	public String toString() {
		return "Comments [commentId=" + commentId + ", comment=" + comment + ", user=" + user + "]";
	}
	
}
