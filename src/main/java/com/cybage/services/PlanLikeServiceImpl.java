package com.cybage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dto.PlanLikeDto;
import com.cybage.entity.PlanLike;
import com.cybage.entity.Plans;
import com.cybage.entity.Users;
import com.cybage.repository.PlanLikeRepository;
import com.cybage.repository.PlansRepository;
import com.cybage.repository.UsersRepository;

@Service
public class PlanLikeServiceImpl implements PlanLikeService {
	
	@Autowired
	PlanLikeRepository planLikeRepository;
	
	@Autowired
	PlansRepository plansRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	// liked plan by user
	@Override
	public PlanLike likePlanByUser(int userId, int planId) {
		PlanLike planLike = new PlanLike();
		Plans plans = plansRepository.findById(planId).get();
		Users user = usersRepository.findById(userId).get();

		planLike.setLikeStatus(true);
		// set user id
		user.setUserId(userId);
		planLike.setUser(user);
		
		// set plan id
		plans.setPlanId(planId);
		// incrementing likes
		plans.setTotalLikes(plans.getTotalLikes()+1);
		//set plan
		planLike.setPlan(plans);
		 return planLikeRepository.save(planLike);
	}

	@Override
	public List<PlanLikeDto> getPlanLikedByUser(int userId) {
		return planLikeRepository.getPlanLikedByUser(userId);
	}

	// dislike plan by user
	@Override
	public PlanLike disLikePlanByUser(int userId, int planId) {
		PlanLike planLike = new PlanLike();
		Plans plans = plansRepository.findById(planId).get();
		Users user = usersRepository.findById(userId).get();
		planLike.setLikeStatus(false);
		// set user id
		user.setUserId(userId);
		planLike.setUser(user);
		
		// set plan id
		plans.setPlanId(planId);
		// decrementing likes
		plans.setTotalDisLikes(plans.getTotalDisLikes()+1);
		//set plan
		planLike.setPlan(plans);
		 return planLikeRepository.save(planLike);
	}
}
