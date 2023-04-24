package com.cybage.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Otp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer otpId;
	private String otpValue;
	@OneToOne(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
	@JoinColumn(name="userId")
	private Users user;
	public Otp() {
		super();
	}

	public Otp(Integer otpId, String otpValue, Users user) {
		super();
		this.otpId = otpId;
		this.otpValue = otpValue;
		this.user = user;
	}
	
	public Otp(String otpValue, Users user) {
		super();
		this.otpValue = otpValue;
		this.user = user;
	}

	public Integer getOtpId() {
		return otpId;
	}
	public void setOtpId(Integer otpId) {
		this.otpId = otpId;
	}
	
	public String getOtpValue() {
		return otpValue;
	}

	public void setOtpValue(String otpValue) {
		this.otpValue = otpValue;
	}

	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	//to String
	@Override
	public String toString() {
		return "Otp [otpId=" + otpId + ", otpValue=" + otpValue + ", user=" + user + "]";
	}
	
}
