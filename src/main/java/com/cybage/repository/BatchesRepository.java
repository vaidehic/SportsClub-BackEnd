package com.cybage.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cybage.entity.Batches;
import com.cybage.entity.Sports;

@Repository
@Transactional
public interface BatchesRepository extends JpaRepository<Batches, Integer> {

	List<Batches> findAllBySport(Sports sport);
}
