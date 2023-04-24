package com.cybage.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.cybage.dto.ManagerDto;
import com.cybage.entity.Otp;
import com.cybage.entity.Roles;
import com.cybage.entity.Sports;
import com.cybage.entity.UserProfile;
import com.cybage.entity.Users;
import com.cybage.exception.BadCreateRequestException;
import com.cybage.exception.ResourceNotFoundException;
import com.cybage.repository.OtpRepository;
import com.cybage.repository.SportsRepository;
import com.cybage.repository.UserProfileRepository;
import com.cybage.repository.UsersRepository;
import com.cybage.util.EmailUtil;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	SportsRepository sportsRepository;

	@Autowired
	OtpRepository otpRepository;

	@Autowired
	UserProfileRepository userProfileRepository;

	@Autowired
	EmailUtil emailUtil;

	private static final Logger LOGGER=LoggerFactory.getLogger(UsersServiceImpl.class);



	@Override
	public List<Users> getusers() {
		return usersRepository.findAll();
	}

	@Override
	public Users addUser(Users user) {
		LOGGER.info(user.getName() +" has been added");
		return usersRepository.save(user);
	}

	@Override
	public List<ManagerDto> getManagers(){
		return usersRepository.getAllManagers().stream().
				filter(m->m.getRole()==Roles.MANAGER).collect(Collectors.toList());
	}



	@Override
	public int addManager(Users user,int id) throws AddressException, MessagingException {
		//emailUtil.sendEmail("mukeshkumars@cybage.com", "Manager Login Details" , ""
			//	+ "Hello "+user.getName()+","+"\n"+"Email is: "+user.getEmail()+" and password is "+user.getPassword());
		user.setRole(Roles.MANAGER);
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setAccountNonLocked(true);
		user.setRequestForUnlock(false);
		user.setNoOfLoginAttempts(0);
		Sports sport=sportsRepository.findById(id).get();
		Set<Sports> sports
		= new HashSet<>(); 
		sports.add(sport);
		user.setSport(sports);
		LOGGER.info(user.getName() +" has been registered as a manager");
		usersRepository.save(user);
		return 1;
	}

	@Override
	public Users register(Users user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setRole(Roles.MEMBER);
		user.setAccountNonLocked(true);
		user.setRequestForUnlock(false);
		user.setNoOfLoginAttempts(0);
		LOGGER.info(user.getName() +" has been registered as a Member");
		return usersRepository.save(user);
	}
	// forget password
	@Override
	public int forgetPassword(String email) throws AddressException, MessagingException {
		// getting user
		Users user=usersRepository.findByEmail(email);
		if(user!=null){
			String otpValue= ""+Math.round(Math.random()*10000);
			Otp otpentity=otpRepository.findByUser(user);
			if(otpentity!=null) {
				Otp otp = new Otp(otpentity.getOtpId(),otpValue, user);
				emailUtil.sendEmail("mukeshkumars@cybage.com", "verify otp",otpValue);
				otpRepository.save(otp);
				return 1;
			}
			else {
				Otp otp = new Otp(otpValue, user);
				emailUtil.sendEmail("mukeshkumars@cybage.com", "verify otp",otpValue);
				otpRepository.save(otp);
				return 1;
			}

		}else {
			// message to user not valid user
			return 0;
		}
	}
	// VERIFY OTP
	@Override
	public int verifyOtp(String otp, String email) {
		Users user=usersRepository.findByEmail(email);
		Otp otpentity=otpRepository.findByUser(user);
		LOGGER.info(otpentity.getUser().getName() +" is verifying OTP ");
		if(otpentity.getOtpValue().equals(otp)) {
			otpRepository.save(otpentity);
			return 1;
		}

		else
			return 0;
	}

	@Override
	public int updatePassword(String pass, String email) {
		Users user=usersRepository.findByEmail(email);
		user.setPassword(new BCryptPasswordEncoder().encode(pass));
		usersRepository.save(user);
		LOGGER.info(user.getName() +" password changed succesfully");
		return 1;
	}

	@Override
	public Users getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	//login attempts
	@Override
	public int noOfLoginAttempts(String email) {
		Users user=usersRepository.findByEmail(email);
		if(user.getNoOfLoginAttempts()==2)
		{
			user.setNoOfLoginAttempts(user.getNoOfLoginAttempts()+1);
			user.setAccountNonLocked(false);
		}
		else
		{	
			user.setNoOfLoginAttempts(user.getNoOfLoginAttempts()+1);
		}

		usersRepository.save(user);
		return user.getNoOfLoginAttempts();
	}


	public Users unlockAccount(String email,String password)
	{
		Users user=usersRepository.findByEmail(email);
		user.setAccountNonLocked(true);
		user.setNoOfLoginAttempts(0);
		user.setRequestForUnlock(false);
		user.setPassword(new BCryptPasswordEncoder().encode(password));
		LOGGER.info(user.getName() +" account has been unlocked");
		return usersRepository.save(user);
	}

	public List<Users> getUserByRequestForUnlock()
	{
		List<Users> users = new ArrayList<>();
		for (Users u :usersRepository.findAll()) {
			if (u.isRequestForUnlock()) { 
				users.add(u);
			}
		}

		return users;
	}

	@Override
	public int deleteManger(int id) throws ResourceNotFoundException {
		Users user=usersRepository.findById(id).get();
		Set<Sports> sportset= new HashSet<>();
		user.setSport(sportset);
		LOGGER.info(user.getName() +"- Manager deleted");
		usersRepository.delete(user);
		return 1;
	}

	@Override
	public Users requestForUnlockAccount(String email) {
		Users user=usersRepository.findByEmail(email);
		user.setRequestForUnlock(true);
		return usersRepository.save(user);
	}

	@Override
	public boolean changePassword(String oldpass, String newpass, String email) {
		Users user=usersRepository.findByEmail(email);
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();

		if(b.matches(oldpass, user.getPassword()))
		{
			user.setPassword(new BCryptPasswordEncoder().encode(newpass));
			usersRepository.save(user);
			LOGGER.info(user.getName() +"password changed successfully");
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public Users getManagerById(int id) {

		return usersRepository.findById(id).get();

	}

	@Override
	public Users updateManager(int managerId, int sportId, Users user) throws BadCreateRequestException {
		user.setRole(Roles.MANAGER);
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setAccountNonLocked(true);
		user.setRequestForUnlock(false);
		user.setNoOfLoginAttempts(0);
		Sports sport=sportsRepository.findById(sportId).get();
		Set<Sports> sports
		= new HashSet<>(); 
		sports.add(sport);
		user.setSport(sports);
		LOGGER.info(user.getName() +"- Manager details updated");
		return usersRepository.save(user);

	}

	@Override
	public UserProfile registerProfile(int profileId, UserProfile userprofile) {
		System.out.println("Inside ServiceImpl");
		System.out.println(profileId);
		userprofile.setProfileId(profileId);
		System.out.println(userprofile);
		return userProfileRepository.save(userprofile);
	}

	@Override
	public UserProfile getProfile(int userId) {
		return userProfileRepository.findById(userId).get();
		
	}

	

}
