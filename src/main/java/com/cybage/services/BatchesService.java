package com.cybage.services;

import java.util.List;

import com.cybage.entity.Batches;
import com.cybage.exception.BadCreateRequestException;
import com.cybage.exception.ResourceNotFoundException;

public interface BatchesService {
	//add batch
    public Batches addBatch(Batches batch,int id) throws BadCreateRequestException; 
    //delete batch
    public int deleteBatch(int id) throws ResourceNotFoundException ;
    //update batch
    public Batches updateBatch(Batches batch, int batchId, int sportId) throws BadCreateRequestException;
	//get one batch
    public Batches getbatch(int id) throws ResourceNotFoundException ;
    public List<Batches> getAllbatchesById(int sportId);
	public int decreaseBacthSize(int batchId);
	public List<Batches> getAllbatchesByManager(int managerId);
	public List<Batches> getAllBatches();
}
