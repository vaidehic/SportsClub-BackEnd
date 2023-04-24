package com.cybage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cybage.dto.PlansDto;
import com.cybage.entity.Batches;
import com.cybage.entity.Comments;
import com.cybage.entity.EnrollmentStatus;
import com.cybage.entity.Enrollments;
import com.cybage.entity.Plans;
import com.cybage.entity.Sports;
import com.cybage.repository.BatchesRepository;
import com.cybage.repository.CommentsRepository;
import com.cybage.repository.EnrollmentRepository;
import com.cybage.repository.PlansRepository;
import com.cybage.services.BatchesService;
import com.cybage.services.BatchesServiceImpl;
import com.cybage.services.CommentServiceImpl;
import com.cybage.services.EnrollmentsServiceImpl;
import com.cybage.services.PlansServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class DeccanSportClubApplicationTests {

	//@Autowired
	 @InjectMocks
	private BatchesServiceImpl batchesserviceimpl;
	 
	 @InjectMocks
		private PlansServiceImpl planserviceimpl;
	
	 @InjectMocks
		private CommentServiceImpl commentserviceimpl;

	 
	 @InjectMocks
		private EnrollmentsServiceImpl enrollementserviceimpl;
	 //@MockBean
	@Mock
	private BatchesRepository batchesRepository;
	
	@Mock
	private PlansRepository plansRepository;
	
	@Mock
	private CommentsRepository commentsRepository;
	
	@Mock
	private EnrollmentRepository enrollmentRepository;
	
	  @Before
	    public void init() {
	        MockitoAnnotations.initMocks(this);
	    }
	
	//for setup check
	 @Test
	  public void test() {
	    List<String> mockList = mock(List.class);
	    mockList.add("First");
	    when(mockList.get(0)).thenReturn("Mockito");
	    when(mockList.get(1)).thenReturn("JCG");
	    assertEquals("Mockito", mockList.get(0));
	    assertEquals("JCG", mockList.get(1));
	  }
	
	 
	@Test
	public void getAllBatchesTest1()
  {
  	List<Batches>batches=batchesRepository.findAll();
    assertNotNull(batches);
  }
	
	@Test
	public void getAllBatchesTest2()
	  {
		  List<Batches> list = new ArrayList<Batches>();
		  Batches batch1=new Batches(LocalTime.now(),LocalTime.now(),30);
		  Batches batch2=new Batches(LocalTime.now(),LocalTime.now(),20);
		  Batches batch3=new Batches(LocalTime.now(),LocalTime.now(),10);
		  list.add(batch1);
		  list.add(batch2);
		  list.add(batch3);
		  when(batchesRepository.findAll()).thenReturn(list);
		  List<Batches> newList=batchesserviceimpl.getAllBatches();
		  assertEquals(3, newList.size());
	  }
	
//	 @Test
//	    public void getBatchByIdTest1()
//	    {
//	         Batches batch=batchesserviceimpl.getbatch(1);
//	          assertNotNull(batch);
//	    }
	 
//	 @Test
//	    public void getBatchByIdTest2()
//	    {
//		 Batches batch=new Batches(1,LocalTime.now(),LocalTime.now(),30);
//		 when(batchesRepository.findById(1).get()).thenReturn(batch);
//	         Batches batch1=batchesserviceimpl.getbatch(1);
//	          assertEquals (new Integer(1),batch1.getBatchId());
//
//	    }
//	
//	 @Test
//	    public void createBatchesTest()
//	    {
//		 
//	        Batches batch = new Batches(1,LocalTime.now(),LocalTime.now(),30);
//	        Sports sport = new Sports(1,"Cricket");
//	        int id=sport.getSportId();
//	        batchesserviceimpl.addBatch(batch, id);
//	         
//           verify(batchesRepository, times(1)).save(batch);
//	    }
	
//	  @Test
//	    public void deleteTest() throws Exception {
//	        // given
//		  Batches batch = new Batches(2,LocalTime.now(),LocalTime.now(),30);
//
//	        Mockito.when(batchesRepository.findById(2).get())
//	                .thenReturn(batch);
//
//	        // when
//	        final int result = batchesserviceimpl.deleteBatch(2);
//
//	        // then
//	        Mockito.verify(batchesRepository, times(1))
//	                .delete(batch);
//	        assertEquals(1,result);
//	  }

//Plans**************************
	 
	@Test
	public void getAllplansTest1()
  {
  	List<Plans>plans=plansRepository.findAll();
    assertNotNull(plans);
  }
	
//	@Test
//	public void getAllplansTest2()
//	  {
//		  List<Plans> list = new ArrayList<Plans>();
//		  Plans plan1=new Plans(1,"Plan1",LocalDate.now(),LocalDate.now(),"3",2000.00);
//		  Plans plan2=new Plans(2,"Plan2",LocalDate.now(),LocalDate.now(),"2",1000.00);
//		
//		  list.add(plan1);
//		  list.add(plan2);
//		  when(plansRepository.findAll()).thenReturn(list);
//		  List<PlansDto> newList=planserviceimpl.getAllPlans();
//		  assertEquals(0, newList.size());
//	  }

	@Test
	public void getAllCommentsTest1()
  {
  	List<Comments>comments=commentsRepository.findAll();
    assertNotNull(comments);
  }
	
	
	@Test
	public void getAllCommentsTest2()
	  {
		List<Comments>comments= new ArrayList<Comments>();
		 Comments cmt1=new Comments(1,"Very Good",LocalDateTime.now());
		 Comments cmt2=new Comments(2,"Good",LocalDateTime.now());
		 comments.add(cmt1);
		 comments.add(cmt1);
		  when(commentsRepository.findAll()).thenReturn(comments);
		  List<Comments> newList=commentserviceimpl.getAllComments();
		  assertEquals(2, newList.size());
	  }
	
//************Enrollment****************
	
	@Test
	public void getAllEnrollmentsTest1()
  {
  	List<Enrollments>enrollment=enrollmentRepository.findAll();
    assertNotNull(enrollment);
  }
	
	
	@Test
	public void getAllEnrollmentsTest2()
	  {
		List<Enrollments>enrollment= new ArrayList<Enrollments>();
		Enrollments enroll1=new Enrollments(1,2000.00,EnrollmentStatus.PENDING);
		Enrollments enroll2=new Enrollments(2,3000.00,EnrollmentStatus.PENDING);
		enrollment.add(enroll1);
		enrollment.add(enroll2);
		  when(enrollmentRepository.findAll()).thenReturn(enrollment);
		  List<Object> newList=enrollementserviceimpl.getEnrollmentReport();
		  assertEquals(2, newList.size());
	  }
	
	
	
}
	

