package com.cybage.entity;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Sports {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sportId;
	@Column(nullable = false)
	private String sportName;
	
//	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Plans.class)
//	@JoinColumn(name = "sport_id",referencedColumnName = "sportId")
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "sport")
	private List<Plans> plan;
	
	@Lob
	private byte[] sportImage;
	
	public Sports() {
		super();
	}

	public Sports(String sportName, List<Plans> plan, byte[] sportImage) {
		super();
		this.sportName = sportName;
		this.plan = plan;
		this.sportImage = sportImage;
	}
	public Sports(String sportName, byte[] sportImage) {
		super();
		this.sportName = sportName;
		this.sportImage = sportImage;
	}
	
	public Sports(Integer id,String sportName) {
		super();
		this.sportId=id;
		this.sportName = sportName;
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

	public List<Plans> getPlan() {
		return plan;
	}

	public void setPlan(List<Plans> plan) {
		this.plan = plan;
	}
	
	public byte[] getSportImage() {
		return sportImage;
	}

	public void setSportImage(byte[] sportImage) {
		this.sportImage = sportImage;
	}

	@Override
	public String toString() {
		return "Sports [sportId=" + sportId + ", sportName=" + sportName + ", plan=" + plan + ", sportImage="
				+ Arrays.toString(sportImage) + "]";
	}
	
}
