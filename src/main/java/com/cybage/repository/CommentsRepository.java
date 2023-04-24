package com.cybage.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cybage.entity.Comments;

@Repository
@Transactional
public interface CommentsRepository extends JpaRepository<Comments, Integer> {
	
	@Query(value = "select * from comments where plan_id=?1",nativeQuery = true)
	List<Comments> findByPlan(int planId);

	@Modifying
	@Query(value = "delete from comments where comment_id=?1",nativeQuery = true)
	void deleteCommentById(int commentId);

}
