package com.cybage.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.entity.Batches;
import com.cybage.entity.EnrollmentStatus;
import com.cybage.entity.Enrollments;
import com.cybage.entity.Sports;
import com.cybage.exception.EnrollmentServiceException;
import com.cybage.repository.BatchesRepository;
import com.cybage.repository.EnrollmentRepository;
import com.cybage.repository.PlansRepository;
import com.cybage.repository.SportsRepository;
import com.cybage.repository.UsersRepository;

@Service
public class EnrollmentsServiceImpl implements EnrollmentsService {

	@Autowired
	EnrollmentRepository enrollmentsRepository;
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	SportsRepository sportsRepository;
	@Autowired
	PlansRepository plansRepository;
	@Autowired
	BatchesRepository batchesRepository;

	private static final Logger LOGGER=LoggerFactory.getLogger(EnrollmentsServiceImpl.class);


	//Getting All enrollments for testing
	@Override
	public List<Enrollments> getAllEnrollments(){

		return  enrollmentsRepository.findAll();
	}

	//Getting enrollment details
	@Override
	public List<Enrollments> getUserEnrollments(int userId) throws EnrollmentServiceException{
		List<Enrollments> enrollments = new ArrayList<>();
		for (Enrollments e : enrollmentsRepository.findAll()) {
			if (e.getUser().getUserId() == userId) { 
				enrollments.add(e);
			}
		}
		return enrollments;
	}

	//Adding enrollment details
	@Override
	public Enrollments addUserEnrollment(int userId,int sportId,int planId,double amount,LocalDate planstartDate,LocalDate planendDate){
		Enrollments enrollment = new Enrollments();
		enrollment.setAmountPaid(amount);
		enrollment.setEnrollmentStatus(EnrollmentStatus.PENDING);
		enrollment.setPlan(plansRepository.getOne(planId));
		enrollment.setUser(usersRepository.getOne(userId));
		enrollment.setSport(sportsRepository.getOne(sportId));
		enrollment.setPlanStartDate(planstartDate);
		enrollment.setPlanEndDate(planendDate);
		LOGGER.info("enrollment details added");
		return enrollmentsRepository.save(enrollment);
	}

	//renew membership
	@Override
	public int renewMembership(int enrollmentId) {
		Enrollments enrollment =  enrollmentsRepository.getOne(enrollmentId);
		enrollment.setEnrollmentStatus(EnrollmentStatus.PENDING);
		enrollment.setBatch(null);
		LocalDate newPlanStartDate = enrollment.getPlanEndDate().plusDays(1);
		int planDuration = Integer.parseInt(enrollment.getPlan().getPlanDuration());
		LocalDate newPlanEndDate = newPlanStartDate.plusMonths(planDuration);
		enrollment.setPlanStartDate(newPlanStartDate);
		enrollment.setPlanEndDate(newPlanEndDate);
		LOGGER.info("membership renewed of "+enrollment.getUser().getName());
		enrollmentsRepository.save(enrollment);
		return 1;
	}

	//get all pending requests with managerId
	@Override
	public List<Enrollments> getAllPendingEnrollments(int managerId) {

		List<Enrollments> newlist= new ArrayList<>();
		List<Sports> sports=sportsRepository.getSportsByManagerId(managerId);
		List<Integer> sportIds = sports.stream()
				.map(Sports::getSportId).collect(Collectors.toList());

		List<Enrollments> enroll=
				enrollmentsRepository.findByEnrollmentStatus(EnrollmentStatus.PENDING);

		for (Integer sId : sportIds) {
			for(Enrollments e : enroll)
			{
				if(e.getSport().getSportId()==sId)
				{
					newlist.add(e);
				}
			}
		}
		return newlist;
	}

	//get enrollments by enrollmentId
	@Override
	public Enrollments getEnrollmentById(int enrollmentId) {
		return enrollmentsRepository.findById(enrollmentId).get();
	}


	//	reject member without reason
	@Override
	public Enrollments rejectMember(int enrollmentId) {
		Enrollments enrollment =  enrollmentsRepository.getOne(enrollmentId);
		enrollment.setEnrollmentStatus(EnrollmentStatus.REJECTED);
		enrollmentsRepository.save(enrollment);
		LOGGER.info(enrollment.getUser().getName()+" user rejected to enroll in "+enrollment.getSport().getSportName()+" in "+enrollment.getPlan().getPlanName());
		return enrollment;
	}

	@Override
	public Enrollments acceptMember(int enrollmentId, int batchId) {
		Enrollments enrollment =  enrollmentsRepository.getOne(enrollmentId);
		enrollment.setEnrollmentStatus(EnrollmentStatus.APPROVED);
		Batches batch=batchesRepository.findById(batchId).get();
		enrollment.setBatch(batch);
		LOGGER.info(enrollment.getUser().getName()+" -user accepted in "+enrollment.getSport().getSportName()+" in "+enrollment.getPlan().getPlanName());
		enrollmentsRepository.save(enrollment);
		return null;
	}

	@Override
	public List<Object> getEnrollmentReport() {
		List<Object> newlist= new ArrayList<>();

		List<String> enrollmentStatus=enrollmentsRepository.getEnrollmentStatus();

		newlist.add(enrollmentStatus);

		List<Number> enrollmentCount=enrollmentsRepository.getEnrollmentCount();
		newlist.add(enrollmentCount);
		return newlist;
	}
}


