package com.cybage.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dto.PlansDto;
import com.cybage.entity.Plans;
import com.cybage.entity.Sports;
import com.cybage.repository.PlansRepository;
import com.cybage.repository.SportsRepository;


@Service
public class PlansServiceImpl implements PlansService {

	private LocalDate endDate;

	private static final Logger LOGGER=LoggerFactory.getLogger(PlansServiceImpl.class);

	@Autowired 
	PlansRepository plansRepository;

	@Autowired
	SportsRepository sportRepository;

	//get all plans/offers
	@Override
	public List<PlansDto> getAllPlans() {
		return plansRepository.getAllPlans();
	}

	//method to find planEndDate
	public LocalDate findEndDate(LocalDate startDate,
			int planDuration) {
		return startDate.plusMonths(planDuration);
	}

	//add new plan/offer
	@Override
	public Plans addPlan(Plans plan,int id) {
		//find plan end date from given plan start date and given duration in months
		endDate= findEndDate(plan.getPlanStartDate(),Integer.parseInt(plan.getPlanDuration()));
		plan.setPlanEndDate(endDate);
		plan.setTotalLikes(0.0);
		plan.setTotalDisLikes(0.0);
		Sports sport=sportRepository.findById(id).get();
		plan.setSport(sport);
		LOGGER.info("new plan added");
		return plansRepository.save(plan);

	}

	
	//update particular plan/offer
	@Override
	public Plans updatePlan(Plans plan,int planId,int sportId) {
		//find plan end date from given plan start date and given duration in months
		endDate= findEndDate(plan.getPlanStartDate(),Integer.parseInt(plan.getPlanDuration()));

		plan.setPlanEndDate(endDate);
		plan.setPlanId(planId);
		plan.setSport(sportRepository.findById(sportId).get());
		return plansRepository.saveAndFlush(plan);

	}

	//find plan/offer by id
	@Override
	public Plans getPlanById(int planId){
		return plansRepository.findById(planId).get();
	}

	//Delete plan by id
	public void deletePlan(int id) {
		plansRepository.deleteById(id); 
	}

	//get  plans according to manager
	public List<PlansDto> getPlansByManager(int managerId) {

		List<PlansDto> newlist= new ArrayList<>();
		List<Sports> sports= sportRepository.getSportsByManagerId(managerId);
		List<Integer> sportIds = sports.stream()
				.map(Sports::getSportId).collect(Collectors.toList());

		List<PlansDto> plan =plansRepository.getAllPlansByManager(managerId);
		for (Integer sId : sportIds) {
			for(PlansDto p : plan)
			{
				if(p.getSportId()==sId)
				{
					newlist.add(p);
				}
			}
		}
		return newlist;
	}

	@Override
	public List<Object> getPlanReport() {
		List<Object> newlist= new ArrayList<>();

		List<Plans> plans=plansRepository.findAll();
		List<String> pnames = plans.stream()
				.map(Plans::getPlanName).collect(Collectors.toList());

		newlist.add(pnames);

		List<Double> likes = plans.stream()
				.map(Plans::getTotalLikes).collect(Collectors.toList());

		newlist.add(likes);

		List<Double> dislikes = plans.stream()
				.map(Plans::getTotalDisLikes).collect(Collectors.toList());

		newlist.add(dislikes);

		return newlist;
	}
}
