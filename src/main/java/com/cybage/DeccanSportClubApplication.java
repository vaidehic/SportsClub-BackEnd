package com.cybage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cybage.entity.Roles;
import com.cybage.entity.Users;
import com.cybage.repository.UsersRepository;
import com.cybage.util.EmailUtil;

@SpringBootApplication
public class DeccanSportClubApplication implements CommandLineRunner{
	
	 @Autowired
	 UsersRepository ur;
	    
	public static void main(String[] args) {
		SpringApplication.run(DeccanSportClubApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//    
//	Users user = new Users(5,"s","s12@gmail.com","8798989898",new BCryptPasswordEncoder().encode("1234"),Roles.MEMBER);
//		ur.save(user);

    
// 		Users user = new Users(6,"s","s2@gmail.com","7798989899",new BCryptPasswordEncoder().encode("1234"),Roles.MANAGER);
// 		ur.save(user);
//		EmailUtil em = new EmailUtil();
//		em.sendEmail("mukeshkumars@cybage.com", "test", "otp test");
	}

}
