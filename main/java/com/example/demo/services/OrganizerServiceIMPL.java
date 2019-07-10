package com.example.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Marathon;
import com.example.demo.repo.MarathonRepo;

@Service
public class OrganizerServiceIMPL implements OrganizerService{

	@Autowired
	MarathonRepo marathonRepo;
	@Override
	public boolean insertNewMarathon(Marathon marathon) {
		if(marathon == null) {
			return false;
		}
		Marathon marathonTemp = marathonRepo.findByNameAndDistanceAndPlaceAndDateAndTime(marathon.getName(), marathon.getDistance(), marathon.getPlace(), marathon.getDate(), marathon.getTime());
		if(marathonTemp != null) {
			return false;
		}else {
			marathonRepo.save(marathon);
			return false;
		}
	}
	@Override
	public Marathon selectById(int id) {
		
		//FIND ONE
		Marathon carTemp  = marathonRepo.findById(id).get();
		if(carTemp != null) {
			return carTemp;
		}
		
		return null;
	}
	
	@Override
	public boolean updateMarathonById(Marathon marathon, int id) {
		if(marathonRepo.existsById(id) && marathon != null) {
			Marathon marathonUpdate = marathonRepo.findById(id).get();
			marathonUpdate.setName(marathon.getName());
			marathonUpdate.setDistance(marathon.getDistance());
			marathonUpdate.setPlace(marathon.getPlace());
			marathonUpdate.setDate(marathon.getDate());
			marathonUpdate.setTime(marathon.getTime());
			marathonRepo.save(marathonUpdate);
			return true;
		}
		return false;
	}
}
