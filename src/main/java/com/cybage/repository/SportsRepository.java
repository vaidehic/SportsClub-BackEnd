package com.cybage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.entity.Roles;
import com.cybage.entity.Sports;
import com.cybage.entity.Users;

public interface SportsRepository extends JpaRepository<Sports, Integer> {

	@Query(value="select s.sport_name from  sports s inner join enrollments e on s.sport_id=e.sport_id group by s.sport_id",nativeQuery=true)
	List<Object> getSportForReport();
	
	@Query(value="select count(e.enrollment_id) from  sports s inner join enrollments e on s.sport_id=e.sport_id group by s.sport_id",nativeQuery=true)
	List<Object> getSportcountReport();
	@Query(value="select * from sports where  manager_id IS NULL", nativeQuery=true)
	List<Sports> getSportsForManager();

	
	@Query(value="select * from sports where  manager_id =?1", nativeQuery=true)
	public List<Sports> getSportsByManagerId(int managerId);
	
}
