package com.cybage.services;

import java.time.LocalDate;
import java.util.List;

import com.cybage.entity.Enrollments;
import com.cybage.exception.EnrollmentServiceException;

public interface EnrollmentsService
{
	public List<Enrollments> getUserEnrollments(int userId) throws EnrollmentServiceException;
	public Enrollments addUserEnrollment(int userId,int sportId,int planId,double amountPaid,LocalDate planstartDate ,LocalDate planendDate);
	public int renewMembership(int enrollmentId);
	public List<Enrollments> getAllPendingEnrollments(int userId);
//	public Enrollments rejectMember(int enrollmentId,String reason);
	public Enrollments getEnrollmentById(int enrollmentId);
	public Enrollments rejectMember(int enrollmentId);
	public Enrollments acceptMember(int enrollmentId, int batchId);
	public List<Object> getEnrollmentReport();
	List<Enrollments> getAllEnrollments(); //for testing
}
