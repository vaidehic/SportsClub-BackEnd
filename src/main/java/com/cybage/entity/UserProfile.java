package com.cybage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserProfile {
	
	@Id
	private int profileId;
	
	private String name;

	private int age;
	
	private int height;

	private int weight;
	
	private String contactNo;
	
	private String bloodGroup;
	
	private String gender;

	private String mHistory;
	
	public UserProfile() {
		super();
	}
	
	public UserProfile(int profileId, String name, int age, int height, int weight, String contactNo, String bloodGroup,
			String gender, String mHistory) {
		super();
		this.profileId = profileId;
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.contactNo = contactNo;
		this.bloodGroup = bloodGroup;
		this.gender = gender;
		this.mHistory = mHistory;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getmHistory() {
		return mHistory;
	}

	public void setmHistory(String mHistory) {
		this.mHistory = mHistory;
	}

	@Override
	public String toString() {
		return "UserProfile [profileId=" + profileId + ", name=" + name + ", age=" + age + ", height=" + height
				+ ", weight=" + weight + ", contactNo=" + contactNo + ", bloodGroup=" + bloodGroup + ", gender="
				+ gender + ", mHistory=" + mHistory + "]";
	}

	

	

	
}
