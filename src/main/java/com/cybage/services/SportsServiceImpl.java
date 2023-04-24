package com.cybage.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cybage.entity.Sports;
import com.cybage.repository.SportsRepository;

@Service
public class SportsServiceImpl implements SportsService {

	private static final Logger LOGGER=LoggerFactory.getLogger(SportsServiceImpl.class);
	@Autowired
	private SportsRepository sportsRepository;

	//get list of sports
	@Override
	public List<Sports> getSports() {
		LOGGER.info("getting sports");
		return sportsRepository.findAll();
	}
	//insert sport 
	@Override
	public Sports addSport(Sports sports) {
		LOGGER.info(sports.getSportName()+" sport added");
		return sportsRepository.saveAndFlush(sports);
	}

	//get sport by id
	@Override
	public Sports getSportById(int sportId){
		return sportsRepository.findById(sportId).get();	 
	}

	//Update sport
	public Sports updateSport(Sports sport) {
		return sportsRepository.save(sport);
	}

	//delete sport by id
	@Override
	public void deleteSport(int id) {
		LOGGER.info("sport with "+id+ " deleted");
		sportsRepository.deleteById(id);
	}

	// getting reports for sports
	@Override
	public List<Object> getSportForReport() {	
		return sportsRepository.getSportForReport();
	}

	// getting reports count
	@Override
	public List<Object> getSportcountReport() {	
		return sportsRepository.getSportcountReport();
	}

	//get SportList For Manager
	@Override
	public List<Sports> getSportsForManager() {
		return sportsRepository.getSportsForManager();			
	}

	//get SportList By ManagerId
	@Override
	public List<Sports> getSportByManager(int managerId) {
		return sportsRepository.getSportsByManagerId(managerId);
	}

}
