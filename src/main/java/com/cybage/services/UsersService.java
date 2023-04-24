package com.cybage.services;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.cybage.dto.ManagerDto;
import com.cybage.entity.Batches;
import com.cybage.entity.UserProfile;
import com.cybage.entity.Users;
import com.cybage.exception.BadCreateRequestException;
import com.cybage.exception.ResourceNotFoundException;

public interface UsersService {
	public Users register(Users user);

	public int forgetPassword(String email) throws AddressException, MessagingException;

	public List<Users> getusers();

	public int verifyOtp(String otp, String email);

	public int updatePassword(String pass, String email);

	public Users getUserByEmail(String email);
	
	public List<ManagerDto> getManagers();
	public int noOfLoginAttempts(String email);
	
	public int addManager(Users user, int id) throws AddressException, MessagingException;

	public int deleteManger(int id) throws ResourceNotFoundException;
	
	public Users unlockAccount(String email,String password);
	
	public List<Users> getUserByRequestForUnlock();
	public Users requestForUnlockAccount(String email);

	public boolean changePassword(String oldpass, String newpass, String email);
	
	public Users getManagerById(int id);

	 public Users updateManager(int managerId,int sportId,Users user) throws BadCreateRequestException;

	public Users addUser(Users user);

	//for userProfile registration
	public UserProfile registerProfile(int profileId, UserProfile userprofile);

	//getting user profile by id
	public UserProfile getProfile(int userId);
	 


}
