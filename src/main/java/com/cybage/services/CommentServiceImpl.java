package com.cybage.services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.entity.Comments;
import com.cybage.entity.Plans;
import com.cybage.entity.Users;
import com.cybage.repository.CommentsRepository;
import com.cybage.repository.PlansRepository;
import com.cybage.repository.UsersRepository;

@Service
public class CommentServiceImpl implements CommentsService {

	@Autowired
	UsersRepository usersRepository;
	@Autowired
	CommentsRepository commentsRepository;
	@Autowired
	PlansRepository plansRepository;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CommentServiceImpl.class);


	@Override
	public Object addComments(Comments comment, int userId,int planId) {
		comment.setCommentTime(LocalDateTime.now());
		Users user=usersRepository.findById(userId).get();
		Plans plan=plansRepository.findById(planId).get();

		comment.setUser(user);
		comment.setPlan(plan);
		LOGGER.info("comment is added by "+user.getName()+" in "+plan.getPlanName());
		return commentsRepository.save(comment);
	}

	public List<Comments> getAllComments() {
		return commentsRepository.findAll();
	}


	public List<Comments> getCommentbyPlanId(int planId) {
		return commentsRepository.findByPlan(planId);
	}

	@Override
	public void deleteComment(int commentId) {
		commentsRepository.deleteCommentById(commentId);
	}
}
