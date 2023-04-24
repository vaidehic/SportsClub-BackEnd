package com.cybage.services;

import java.util.List;


import com.cybage.dto.PlansDto;

import com.cybage.entity.PlanLike;

import com.cybage.entity.Plans;


//Service Interface for plan/offers

public interface PlansService {
	
	public List<PlansDto> getAllPlans();
	
	public Plans getPlanById(int planId);
	
	public Plans addPlan(Plans plan,int id);
	
	public Plans updatePlan(Plans plan, int planId,int sportId);
	
	public void deletePlan(int id);

	public List<PlansDto> getPlansByManager(int managerId);
	
	public List<Object> getPlanReport();

	

	

}
