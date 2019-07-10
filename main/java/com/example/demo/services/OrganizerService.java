package com.example.demo.services;


import com.example.demo.model.Marathon;


public interface OrganizerService {
	boolean insertNewMarathon(Marathon marathon);
	boolean updateMarathonById(Marathon car, int id);
	Marathon selectById(int id);

}
