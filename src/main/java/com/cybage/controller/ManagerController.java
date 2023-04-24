package com.cybage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.dto.PlansDto;
import com.cybage.entity.Batches;
import com.cybage.entity.Enrollments;
import com.cybage.entity.Plans;
import com.cybage.entity.Sports;
import com.cybage.exception.BadCreateRequestException;
import com.cybage.exception.EnrollmentServiceException;
import com.cybage.exception.PlanServiceException;
import com.cybage.exception.ResourceNotFoundException;
import com.cybage.services.BatchesServiceImpl;
import com.cybage.services.EnrollmentsServiceImpl;
import com.cybage.services.PlansServiceImpl;
import com.cybage.services.SportsServiceImpl;

@RestController
@RequestMapping("/manager")
@CrossOrigin
public class ManagerController {

	@Autowired
	PlansServiceImpl planServiceImpl;

	@Autowired
	private BatchesServiceImpl batchesServiceImpl;

	@Autowired
	private EnrollmentsServiceImpl enrollmentService;

	@Autowired
	private SportsServiceImpl sportsServiceImpl;

	//get list of all plans/offers available
	@GetMapping("/plans")
	public ResponseEntity<List<PlansDto>> getAllPlans() throws PlanServiceException {

		List<PlansDto> plans=planServiceImpl.getAllPlans();
		if(plans.isEmpty())
			throw new PlanServiceException("No plans/offers available right now..");
		return ResponseEntity.status(HttpStatus.OK).body(plans);
	}

	//get plans according to the manager 	
	@GetMapping("/listplan/{managerId}")
	public ResponseEntity<List<PlansDto>> getPlansByManager(@PathVariable int managerId)
			throws PlanServiceException {			
		List<PlansDto> plans=planServiceImpl.getPlansByManager(managerId);
		if(plans.isEmpty())
			throw new PlanServiceException("No plans/offers available right now..");
		return ResponseEntity.status(HttpStatus.OK).body(plans);
	}

	// get specified plan by Id
	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plans> getPlanById(@PathVariable int planId) throws PlanServiceException {

		Plans plan=planServiceImpl.getPlanById(planId);
		if(plan==null)
			throw new PlanServiceException("No such plan found with id="+ planId);
		return ResponseEntity.status(HttpStatus.OK).body(plan);
	}

	//adding new plan/offer
	@PostMapping("/plan/{id}")
	public ResponseEntity<Plans> addPlan(@RequestBody Plans plan,@PathVariable int id) throws PlanServiceException{
		return new ResponseEntity<>(planServiceImpl.addPlan(plan,id),HttpStatus.OK);
	}

	//Deleting plan
	@DeleteMapping("/plan/{id}")
	public ResponseEntity<Object> deletePlan(@PathVariable int id)throws PlanServiceException{	
		Plans planToDelete=planServiceImpl.getPlanById(id);
		if(planToDelete==null)
			return ResponseEntity.notFound().build();
		planServiceImpl.deletePlan(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//Update requested plan
	@PutMapping("/plan/{planId}/{sportId}")
	public ResponseEntity<Object> updatePlan(@RequestBody Plans plan,@PathVariable int planId,@PathVariable int sportId){
		Plans planToUpdate=planServiceImpl.getPlanById(planId);
		if(planToUpdate==null)
			return ResponseEntity.notFound().build();
		return new ResponseEntity<>(planServiceImpl.updatePlan(plan,planId,sportId),HttpStatus.OK);
	}

	//get all batches
	@GetMapping("/batches")
	public ResponseEntity<List<Batches>> getBatches() {	
		return new ResponseEntity<>(batchesServiceImpl.getAllBatches(),HttpStatus.OK);
	}

	//get one batches
	@GetMapping("/batches/{batchId}")
	public ResponseEntity<Batches> getBatch(@PathVariable int batchId) {	
		return new ResponseEntity<>(batchesServiceImpl.getbatch(batchId),HttpStatus.OK);
	}

	//get all batches by sportName
	@GetMapping("/batch/{sportId}")
	public ResponseEntity<List<Batches>> getBatches(@PathVariable int sportId) {	
		return new ResponseEntity<>(batchesServiceImpl.getAllbatchesById(sportId),HttpStatus.OK);
	}

	//get all batches by ManagerId
	@GetMapping("/batchesbymanager/{managerId}")
	public ResponseEntity<List<Batches>> getBatchesByManager(@PathVariable int managerId) {	
		return new ResponseEntity<>(batchesServiceImpl.getAllbatchesByManager(managerId),HttpStatus.OK);
	}

	//add batch 
	@PostMapping("/batch/{id}")
	public ResponseEntity<Batches> addBatch(@RequestBody Batches batch,@PathVariable int id) throws BadCreateRequestException
	{
		return new ResponseEntity<>(batchesServiceImpl.addBatch(batch,id),HttpStatus.OK);

	}

	//update batch 
	@PutMapping("/batch/{batchId}/{sportId}")
	public ResponseEntity<Batches> updateBatch(@RequestBody Batches batch,
			@PathVariable int batchId,@PathVariable int sportId) throws BadCreateRequestException

	{
		return new ResponseEntity<>(batchesServiceImpl.updateBatch(batch,batchId,sportId),HttpStatus.OK);
	}

	//delete batch by id
	@DeleteMapping("/batch/{id}")
	public ResponseEntity<Object> deleteBatch(@PathVariable int id)  throws ResourceNotFoundException
	{
		return new ResponseEntity<>(batchesServiceImpl.deleteBatch(id),HttpStatus.OK);
	}

	//Getting all enrollment details with status Pending for particular manager
	@GetMapping("/enrollments/{managerId}")
	public ResponseEntity<List<Enrollments>> getAllPendingEnrollments(@PathVariable int managerId) throws EnrollmentServiceException
	{
		List<Enrollments> enrollments = enrollmentService.getAllPendingEnrollments(managerId);
		if(enrollments.isEmpty())
		{
			throw new EnrollmentServiceException("No Pending Request !!");	
		}

		else
		{
			return ResponseEntity.status(HttpStatus.OK).body(enrollments);
		}	
	}

	//get enrollment by enrollmentId
	@GetMapping("/enrollment/{enrollmentId}")
	public ResponseEntity<Enrollments>getEnrollmentById(@PathVariable int enrollmentId) throws EnrollmentServiceException
	{
		Enrollments enrollments = enrollmentService.getEnrollmentById(enrollmentId);
		if(enrollments == null)
		{
			throw new EnrollmentServiceException("No Enrollment found");	
		}

		else
		{
			return ResponseEntity.status(HttpStatus.OK).body(enrollments);
		}	
	}

	//rejecting member without reason
	@DeleteMapping("/reason/{enrollmentId}")
	public ResponseEntity<Enrollments> rejectMember(@PathVariable int enrollmentId) throws EnrollmentServiceException
	{
		Enrollments enrollments = enrollmentService.rejectMember(enrollmentId);
		return ResponseEntity.status(HttpStatus.OK).body(enrollments);
	}

	//accepting member for enrollment
	@GetMapping("/enrollment/{enrollmentId}/{batchId}")
	public ResponseEntity<Enrollments> acceptMember(@PathVariable int enrollmentId,@PathVariable int batchId) throws EnrollmentServiceException
	{
		Enrollments enrollments = enrollmentService.acceptMember(enrollmentId,batchId);
		batchesServiceImpl.decreaseBacthSize(batchId);
		return ResponseEntity.status(HttpStatus.OK).body(enrollments);
	}

	//get sports for report
	@GetMapping("/sports")
	public ResponseEntity<List<Object>> getSportReport(){
		return new ResponseEntity<>(sportsServiceImpl.getSportForReport(),HttpStatus.OK);
	}

	//get sports count for report
	@GetMapping("/count")
	public ResponseEntity<List<Object>> getSportcountReport(){
		return new ResponseEntity<>(sportsServiceImpl.getSportcountReport(),HttpStatus.OK);
	}

	//get plan report--likes and dislikes
	@GetMapping("/planreport")
	public ResponseEntity<List<Object>> getPlanReport(){
		return new ResponseEntity<>(planServiceImpl.getPlanReport(),HttpStatus.OK);
	}

	//get enrollment report
	@GetMapping("/enrollmentreport")
	public ResponseEntity<List<Object>> getEnrollmentReport(){
		return new ResponseEntity<>(enrollmentService.getEnrollmentReport(),HttpStatus.OK);
	}

	//get sports By managerId
	@GetMapping("/sports/{managerId}")
	public ResponseEntity<List<Sports>> getSportsByManager(@PathVariable int managerId){
		return new ResponseEntity<List<Sports>>(sportsServiceImpl.getSportByManager(managerId),HttpStatus.OK);
	}
}
