package com.cybage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cybage.dto.ManagerDto;
import com.cybage.dto.PlanLikeDto;
import com.cybage.entity.Batches;
import com.cybage.entity.PlanLike;
import com.cybage.entity.Plans;
import com.cybage.entity.Roles;
import com.cybage.entity.Sports;
import com.cybage.entity.Users;
import com.cybage.repository.BatchesRepository;
import com.cybage.repository.PlanLikeRepository;
import com.cybage.repository.SportsRepository;
import com.cybage.repository.UsersRepository;
import com.cybage.services.BatchesServiceImpl;
import com.cybage.services.PlanLikeServiceImpl;
import com.cybage.services.SportsServiceImpl;
import com.cybage.services.UsersServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTesting {
	@InjectMocks
	private UsersServiceImpl usersserviceimpl;
	
	@InjectMocks
	private SportsServiceImpl sportsserviceimpl;

	@InjectMocks
	private PlanLikeServiceImpl planlikeserviceimpl;

	
	@Mock
	private UsersRepository userRepository;

	@Mock
	private SportsRepository sportRepository;

	@Mock
	private PlanLikeRepository planLikeRepository;


	
	  @Before
	    public void init() {
	        MockitoAnnotations.initMocks(this);
	    }

	@Test
	public void getAllUsersTest1()
	{
		List<Users>users=userRepository.findAll();
		assertNotNull(users);
	}

	@Test
	public void getAllUsersTest2()
	{
		List<Users>users = new ArrayList<Users>();
		Users user1=new Users(1,"User1","user1@gmail.com","987654320","user1",Roles.MEMBER);
		Users user2=new Users(2,"User2","user2@gmail.com","987654320","user2",Roles.MEMBER);
		Users user3=new Users(3,"User3","user3@gmail.com","987654320","user3",Roles.MEMBER);
		users.add(user1);
		users.add(user2);
		users.add(user3);
		when(userRepository.findAll()).thenReturn(users);
		List<Users> newList=usersserviceimpl.getusers();
		assertEquals(3, newList.size());
	}


	@Test
	public void createUserTest()
	{

		Users user=new Users(1,"User1","user1@gmail.com","987654320","user1",Roles.MEMBER);
		usersserviceimpl.addUser(user);

		verify(userRepository, times(1)).save(user);
	}

	@Test
	public void getAllManagersTest1()
	{
		List<ManagerDto>managers=usersserviceimpl.getManagers();
		assertNotNull(managers);
	}


	@Test
	public void getManagerByRoleTest1()
	{
		List<Users>users=new ArrayList<>();
		Users user1=new Users(4,"User4","user1@gmail.com","987654320","user1",Roles.MANAGER);
		Users user2=new Users(5,"User5","user1@gmail.com","987654320","user1",Roles.MANAGER);
		users.add(user1);
		users.add(user2);

		when(userRepository.findByRole(Roles.MANAGER)).thenReturn(users);
		List<Users> newList=usersserviceimpl.getusers();
		assertEquals(0, newList.size());

	} 

//	@Test
//	public void deleteManager() {
//		List<Users>users=new ArrayList<>();
//		Users user1=new Users(4,"User4","user1@gmail.com","987654320","user1",Roles.MANAGER);
//		Users user2=new Users(5,"User5","user1@gmail.com","987654320","user1",Roles.MANAGER);
//		users.add(user1);
//		users.add(user2);
//		when(userRepository.delete(user1)).thenReturn(1);
//		List<Users> newList=usersserviceimpl.getusers();
//		assertEquals(1, userRepository.delete(user1));
//	}

	
	//Sport*****************************
	
	@Test
	public void getAllSportsTest1()
	{
		List<Sports>sports=sportRepository.findAll();
		assertNotNull(sports);
	}

	@Test
	public void getAllSportsTest2()
	{
		List<Sports>sports= new ArrayList<>();
		Sports sport1=new Sports(1,"Cricket");
		Sports sport2=new Sports(2,"Football");
		Sports sport3=new Sports(3,"Basketball");
		sports.add(sport1);
		sports.add(sport2);
		sports.add(sport3);
		when(sportRepository.findAll()).thenReturn(sports);
		List<Sports> newList=sportsserviceimpl.getSports();
		assertEquals(3, newList.size());
	}

	@Test
	public void createSportTest()
	{

		Sports sport=new Sports(1,"Cricket");
		sportsserviceimpl.addSport(sport);

		verify(sportRepository, times(1)).saveAndFlush(sport);
	}
	

//	@Test
//	public void deleteSport() {
//		List<Sports>sports=new ArrayList<>();
//		Sports sport1=new Sports(1,"Cricket");
//		Sports sport2=new Sports(2,"Football");
//		Sports sport3=new Sports(3,"Basketball");
//		sports.add(sport1);
//		sports.add(sport2);
//		sports.add(sport3);
//		when(sportRepository.delete(sport1)).thenReturn(1);
//		List<Sports> newList=sportsserviceimpl.getSports();
//		assertEquals(2,newList.size());
//	}
	
  @Test
  public void getPlanLiked() {
	  Users user1=new Users(1,"User1","user1@gmail.com","987654320","user1",Roles.MEMBER);
	  List<PlanLikeDto> planLike=planLikeRepository.getPlanLikedByUser(1);
	  assertNotNull(planLike);
	  
  }
  
//  @Test
//  public void likePlan() {
//	  Users user1=new Users(1,"User1","user1@gmail.com","987654320","user1",Roles.MEMBER);
//	  Plans plan1=new Plans(1,"Plan1",LocalDate.now(),LocalDate.now(),"3",2000.00);
//	  PlanLike planLike= planlikeserviceimpl.likePlanByUser(1,1);
//	  assertNotNull(planLike);
//  }
  
  
  
}
