package com.cybage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cybage.dto.PlanLikeDto;
import com.cybage.entity.PlanLike;

public interface PlanLikeRepository extends JpaRepository<PlanLike, Integer> {

	@Query(value = "select new com.cybage.dto.PlanLikeDto(p.likeId,p.likeStatus,u.userId,pl.planId) from PlanLike p"
			+ " join p.user u join p.plan pl where u.userId=?1",nativeQuery = false)
	List<PlanLikeDto> getPlanLikedByUser(int userId);

}
