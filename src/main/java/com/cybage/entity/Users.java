package com.cybage.entity;

import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@Column(nullable = false)
	private String name;
	@Column(unique = true,nullable = false)
	private String email;
	@Column(unique = true,nullable = false)
	private String phoneNumber;
	@Column(nullable =false)
	private String password;
	@Column(nullable =false)
	private int noOfLoginAttempts;
	@Column(columnDefinition = "boolean DEFAULT TRUE")
	private boolean accountNonLocked;
	@Column(columnDefinition = "boolean DEFAULT FALSE")
	private boolean requestForUnlock;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,
			targetEntity = Sports.class)
	@JoinColumn(name = "manager_id",referencedColumnName = "userId")
	@JsonIgnore
	private Set<Sports> sport;

	@Enumerated(EnumType.STRING)
	private Roles role;

	public Users() {
		super();
	}

	public Users(String name, String email, String phoneNumber, String password, Set<Sports> sport, Roles role) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.sport = sport;
		this.role = role;
	}
	public Users(Integer userId, String name, String email, String phoneNumber, String password, Roles role) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.role = role;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Sports> getSport() {
		return sport;
	}

	public void setSport(Set<Sports> sport) {
		this.sport = sport;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}
	
	
	public int getNoOfLoginAttempts() {
		return noOfLoginAttempts;
	}

	public void setNoOfLoginAttempts(int noOfLoginAttempts) {
		this.noOfLoginAttempts = noOfLoginAttempts;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	
	public boolean isRequestForUnlock() {
		return requestForUnlock;
	}

	public void setRequestForUnlock(boolean requestForUnlock) {
		this.requestForUnlock = requestForUnlock;
	}

	//tostring
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", password=" + password + ", sport=" + sport + ", role=" + role + "]";
	}

}
