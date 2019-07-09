package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Marathon;
import com.example.demo.repo.MarathonRepo;

@Service
public class OrganizerServiceImpl implements OrganizerService{

	@Autowired
	MarathonRepo marathonRepo;
	@Override
	public boolean insertNewMarathon(Marathon marathon) {
		if(marathon == null) {
			return false;
		}
		if(marathonRepo.existsById(marathon.getId())) {
			return false;
		}else {
			marathonRepo.save(marathon);
			return false;
		}
	}
	
}