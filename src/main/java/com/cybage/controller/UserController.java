package com.cybage.controller;

import java.time.LocalDate;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import com.cybage.dto.PlanLikeDto;
import com.cybage.entity.Comments;
import com.cybage.entity.Enrollments;
import com.cybage.entity.PlanLike;
import com.cybage.entity.Sports;
import com.cybage.entity.UserProfile;
import com.cybage.entity.Users;
import com.cybage.exception.EnrollmentServiceException;
import com.cybage.exception.ResourceNotFoundException;
import com.cybage.services.CommentsService;
import com.cybage.services.EnrollmentsService;
import com.cybage.services.PlanLikeService;
import com.cybage.services.SportsServiceImpl;
import com.cybage.services.UsersService;


@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	@Autowired
	UsersService userService;

	@Autowired
	CommentsService commentsService;

	@Autowired
	PlanLikeService planLikeService;

	@Autowired
	EnrollmentsService enrollmentsService;

	@Autowired
	SportsServiceImpl sportsServiceImpl;


	@PostMapping("/register")
	public ResponseEntity<Users> register(@RequestBody Users user) {
		return new ResponseEntity<Users>(userService.register(user),HttpStatus.OK);
	}

	
	//register profile of user
	@PostMapping("/addprofile/{userId}")
	public ResponseEntity<UserProfile> register(@PathVariable int userId,@RequestBody UserProfile userprofile) {
		int profileId= userId;
		return new ResponseEntity<UserProfile>(userService.registerProfile(profileId,userprofile),HttpStatus.OK);
	}
	
	//getting user profile by userid
	@GetMapping("/userprofile/{userId}")
	public ResponseEntity<UserProfile> getProfile(@PathVariable int userId) {
		UserProfile user=userService.getProfile(userId);
		if(user==null)
			throw new ResourceNotFoundException("No Profile created");
		else
			return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	//get list of sports
	@GetMapping("/sports")
	public ResponseEntity<List<Sports>> getSports() {
		List<Sports> listSport=sportsServiceImpl.getSports();
		if(listSport.isEmpty())
			throw new ResourceNotFoundException("No Sports available");
		else
			return ResponseEntity.status(HttpStatus.OK).body(listSport);
	}

	// get sport by id
	@GetMapping("sports/{sportId}")
	public ResponseEntity<Sports> findById(@PathVariable int sportId) {
		Sports sport=sportsServiceImpl.getSportById(sportId);
		if(sport==null)
			throw new ResourceNotFoundException("No Sports available");
		else
			return ResponseEntity.status(HttpStatus.OK).body(sport);
	}

	//getting plan liked by user
	@GetMapping("/plan/user/{userId}")
	public ResponseEntity<List<PlanLikeDto>> getPlanLikedByUser(@PathVariable int userId) {
		return new ResponseEntity<List<PlanLikeDto>>(planLikeService.getPlanLikedByUser(userId),HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Users>> getusers() {	
		return new ResponseEntity<List<Users>>(userService.getusers(),HttpStatus.OK);
	}

	// getting all comments(not required)
	@GetMapping("/comments/{planId}")
	public ResponseEntity<List<Comments>> getCommentbyPlanId(@PathVariable int planId) {
		return new ResponseEntity<List<Comments>>(commentsService.getCommentbyPlanId(planId),HttpStatus.OK);
	}
	// adding comments of user
	@PostMapping("/comments/{userId}/{planId}")
	public ResponseEntity<Object> addComments(@RequestBody Comments comment,@PathVariable int userId,@PathVariable int planId) {
		return new ResponseEntity<Object>(commentsService.addComments(comment,userId,planId),HttpStatus.OK);
	}
	
	// delete comment
	@DeleteMapping(value = "/comments/{commentId}")
	public void deleteComment(@PathVariable int commentId) {
		commentsService.deleteComment(commentId);
	}
	
	// plan liked by user
	@PostMapping("/like/{userId}/plan/{planId}")
	public ResponseEntity<PlanLike> likePlanByUser(@PathVariable int userId,@PathVariable int planId) {
		return new ResponseEntity<PlanLike>(planLikeService.likePlanByUser(userId, planId),HttpStatus.OK);
	}
	
	// plan disliked by user
	@PostMapping("/dislike/{userId}/plan/{planId}")
	public ResponseEntity<PlanLike> disLikePlanByUser(@PathVariable int userId,@PathVariable int planId) {
		return new ResponseEntity<PlanLike>(planLikeService.disLikePlanByUser(userId, planId),HttpStatus.OK);
	}

	//Getting enrollment details
	@GetMapping("/enrollment/{userId}")
	public ResponseEntity<List<Enrollments>> getUserEnrollments(@PathVariable int userId) throws EnrollmentServiceException
	{
		List<Enrollments> enrollments = enrollmentsService.getUserEnrollments(userId);
		if(enrollments.isEmpty())
		{
			throw new EnrollmentServiceException("Enrollment data is not present !!");	
		}
		else
		{
			return ResponseEntity.status(HttpStatus.OK).body(enrollments);
		}	
	}

	// user enrolled for sport
	@PostMapping("/addenrollment")
	public ResponseEntity<Enrollments> addUserEnrollment(@RequestPart("userId") String userid,@RequestPart("sportId") String sportid,@RequestPart("planId") String planid,@RequestPart("amountPaid") String amountpaid,
			@RequestPart("planStartDate")  String planStartDate,@RequestPart("planEndDate") String planEndDate)  
	{
		LocalDate planstartDate = LocalDate.parse(planStartDate);
		LocalDate planendDate = LocalDate.parse(planEndDate);
		int userId = Integer.parseInt(userid);
		int sportId = Integer.parseInt(sportid);
		int planId = Integer.parseInt(planid);
		double amountPaid = Double.parseDouble(amountpaid);
		Enrollments enrollment =  enrollmentsService.addUserEnrollment(userId,sportId,planId,amountPaid,planstartDate ,planendDate);
		return ResponseEntity.status(HttpStatus.OK).body(enrollment);
	}

	//Renewing membership
	@PostMapping("/renew")
	public ResponseEntity<Integer> renewMembership(@RequestBody int enrollmentId)  
	{
		int result = enrollmentsService.renewMembership(enrollmentId);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	//forget passowrd
	@PostMapping("/forgetpass")
	public ResponseEntity<Integer> forgetPassword(@RequestBody String email) throws AddressException, MessagingException {
		return ResponseEntity.status(HttpStatus.OK).body(userService.forgetPassword(email));
	}

	//verify otp
	@PostMapping("/verifyotp/{email}")
	public ResponseEntity<Integer> verifyOtp(@RequestBody String otp,@PathVariable String email) throws AddressException, MessagingException {
		return ResponseEntity.status(HttpStatus.OK).body(userService.verifyOtp(otp,email));
	}

	//updating password
	@PostMapping("/updatepass/{email}")
	public ResponseEntity<Integer> updatePassword(@RequestBody String pass,@PathVariable String email) throws AddressException, MessagingException {
		return ResponseEntity.status(HttpStatus.OK).body(userService.updatePassword(pass,email));
	}

	// getting user by email
	@GetMapping("/email/{email}")
	public ResponseEntity<Users> getUserByEmail(@PathVariable String email) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByEmail(email));
	}

	// getting number of login attempts
	@PostMapping("/count/{email}")
	public ResponseEntity<Integer> noOfLoginAttempts(@PathVariable String email)
	{
		return ResponseEntity.status(HttpStatus.OK).body(userService.noOfLoginAttempts(email));
	}

	// unlocking user account
	@PostMapping("/unlock/{email}")
	public  ResponseEntity<Users> requestForUnlockAccount(@PathVariable String email)
	{
		return ResponseEntity.status(HttpStatus.OK).body(userService.requestForUnlockAccount(email));
	}


	//Verify password before change password
	@PostMapping("/changePassword")
	public ResponseEntity<Boolean> changePassword(@RequestPart("oldpass") String oldpass,@RequestPart("newpass") String newpass,@RequestPart("email") String email)
	{
		return ResponseEntity.status(HttpStatus.OK).body(userService.changePassword(oldpass,newpass,email));
	}
}
