package com.cybage.services;

import java.util.List;

import com.cybage.dto.PlanLikeDto;
import com.cybage.entity.PlanLike;

public interface PlanLikeService {

	public PlanLike likePlanByUser(int userId, int planId);

	public List<PlanLikeDto> getPlanLikedByUser(int userId);

	public PlanLike disLikePlanByUser(int userId, int planId);

}
