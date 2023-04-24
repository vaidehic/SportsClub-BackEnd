package com.cybage.services;

import java.util.List;

import com.cybage.entity.Sports;

public interface SportsService {

	List<Sports> getSports();

	Sports addSport(Sports sports);

	Sports getSportById(int sportId);

	void deleteSport(int id);
	
	List<Object> getSportForReport();
	
	List<Object> getSportcountReport();
	
	

	List<Sports> getSportsForManager();

	Object getSportByManager(int managerId);
}
