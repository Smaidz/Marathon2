package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Marathon;
import com.example.demo.model.User;
import com.example.demo.repo.MarathonRepo;
import com.example.demo.repo.UserRepo;

@Service
public class UserServiceIMPL implements UserService{
	
	@Autowired
	MarathonRepo marathonRepo;
	
	public ArrayList<Marathon> findAllMarathons() {
		ArrayList<Marathon> tempList = new ArrayList<Marathon>();
		for(Marathon m:marathonRepo.findAll()) {
			tempList.add(m);
		}
		return tempList;
	}
	
	@Autowired
	UserRepo userRepo;
	
	public boolean addNewUser(User user) {
		if(user != null) {
			User uTemp = userRepo.findByEmail(user.getEmail());
			if(uTemp == null) {
				userRepo.save(user);
				return true;
			}
		}
		return false;
	}
	
}
