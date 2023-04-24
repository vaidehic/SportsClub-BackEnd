package com.cybage.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.entity.Batches;
import com.cybage.entity.Sports;
import com.cybage.exception.BadCreateRequestException;
import com.cybage.exception.ResourceNotFoundException;
import com.cybage.repository.BatchesRepository;
import com.cybage.repository.SportsRepository;

@Service
public class BatchesServiceImpl implements BatchesService {

	@Autowired
	private BatchesRepository batchesRepository;
	@Autowired
	private SportsRepository sportRepository;

	private static final Logger LOGGER=LoggerFactory.getLogger(BatchesServiceImpl.class);


	//get all batches
	@Override
	public List<Batches> getAllBatches() {
		return batchesRepository.findAll();
	}

	//to add batch
	@Override
	public Batches addBatch(Batches batch,int id) throws BadCreateRequestException
	{	
		Sports sport=sportRepository.findById(id).get();
		batch.setSport(sport);
		LOGGER.info("New batch added for "+sport.getSportName());
		return batchesRepository.save(batch);
	}

	//get one batch
	@Override
	public Batches getbatch(int batchId)  throws ResourceNotFoundException{
		return batchesRepository.findById(batchId).get();
	}


	//getting batches for particular manager
	@Override
	public List<Batches> getAllbatchesByManager(int managerId) {
		List<Batches> newlist= new ArrayList<>();
		//getting sports assigned to particular manager
		List<Sports> sports=sportRepository.getSportsByManagerId(managerId);
		List<Integer> sportIds = sports.stream()
				.map(Sports::getSportId).collect(Collectors.toList());	
		List<Batches> batches= batchesRepository.findAll();		
		for (Integer s: sportIds) {
			for (Batches b : batches) {
				if(b.getSport().getSportId()==s)
				{
					newlist.add(b);
				}
			}	
		}
		return newlist;
	}

	//delete batch
	@Override
	public int deleteBatch(int id) throws ResourceNotFoundException {
		//batchesRepository.removeBatch(id);
		batchesRepository.deleteById(id);
		return 1;
	}

	//update batch
	@Override
	public Batches updateBatch(Batches batch, int batchId, int sportId)  throws BadCreateRequestException
	{

		Sports sport=sportRepository.findById(sportId).get();
		batch.setBatchId(batchId);
		batch.setSport(sport);
		return batchesRepository.save(batch);
	}

	//get all batches by sportId
	@Override
	public List<Batches> getAllbatchesById(int sportId) {
		Sports sport = sportRepository.findById(sportId).get();
		return batchesRepository.findAllBySport(sport).stream()
				.filter(batch->batch.getBatchSize()>0)
				.collect(Collectors.toList());	
	}

	//decrease batch size after approve user
	@Override
	public int decreaseBacthSize(int batchId) {
		Batches batch=batchesRepository.findById(batchId).get();
		batch.setBatchSize((batch.getBatchSize()-1));
		batchesRepository.save(batch);
		return 1;
	}
}
