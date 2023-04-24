package com.cybage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybage.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer>{
	
}
