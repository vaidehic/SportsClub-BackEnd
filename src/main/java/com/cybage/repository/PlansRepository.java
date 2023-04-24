package com.cybage.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import com.cybage.dto.PlansDto;
import com.cybage.entity.Plans;

public interface PlansRepository extends JpaRepository<Plans, Integer> {
	
	@Query(value = "select new com.cybage.dto.PlansDto(p.planId, p.planName, p.planStartDate, p.planEndDate,\r\n"
			+ "			p.planDuration, p.planFees, s.sportId, s.sportName) from Plans p join p.sport s",nativeQuery = false)
	public List<PlansDto> getAllPlans();
	
	
	@Query(value = "select new com.cybage.dto.PlansDto(p.planId, p.planName, p.planStartDate, p.planEndDate,\r\n"
			+ "			p.planDuration, p.planFees, s.sportId, s.sportName) from Plans p join p.sport s where  manager_id =?1",nativeQuery = false)
	public List<PlansDto> getAllPlansByManager(int managerId);
	
	
	

}
