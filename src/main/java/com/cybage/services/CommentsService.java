package com.cybage.services;

import java.util.List;

import com.cybage.entity.Comments;


public interface CommentsService {
	public Object addComments(Comments comment, int userId,int planId);
	public List<Comments> getCommentbyPlanId(int planId);
	public void deleteComment(int commentId);
}
