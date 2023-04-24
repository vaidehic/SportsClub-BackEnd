package com.cybage.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cybage.entity.EnrollmentStatus;
import com.cybage.entity.Enrollments;

@Repository
@Transactional
public interface EnrollmentRepository extends JpaRepository<Enrollments, Integer> {

      public List<Enrollments> findByEnrollmentStatus(EnrollmentStatus status);
      
      @Modifying
      @Query(value="select e.enrollmentStatus from Enrollments e group by e.enrollmentStatus",nativeQuery = false)
      List<String> getEnrollmentStatus();
      
      @Query(value="select count(e.enrollmentStatus) from Enrollments e group by e.enrollmentStatus")
      List<Number> getEnrollmentCount();
      
      
}
