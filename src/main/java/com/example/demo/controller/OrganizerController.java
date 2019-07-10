package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import com.example.demo.model.Marathon;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

<<<<<<< HEAD
import com.example.demo.model.Organizer;
import com.example.demo.services.OrganizerServiceImpl;

@Controller
@RequestMapping(value = "/o")

public class OrganizerController {
	@Autowired
	OrganizerServiceImpl organizerServiceImpl;
	
	@GetMapping(value = "/view-org")
	public String vieworg(Model model) {
		model.addAttribute("object", organizerServiceImpl.selectAll());
	return "view-org";
	}
	
	@GetMapping(value = "/add-org")
	public String addorg(Organizer organizer)
	{
		return "add-org";
	}
	
	@PostMapping(value = "/add-org")
	public String addorgPost(@Valid Organizer organizer, BindingResult result)
	{
		if(result.hasErrors())
			return "view-org";
		else
		{
		organizerServiceImpl.addNewOrganizer(organizer);
		return "redirect:/a/view-org";
		}
	}
	
	@GetMapping(value = "/export-data")
	public String exportData(Model model) {
		organizerServiceImpl.exportDataExcel();
		//model.addAttribute("object", );
	return "export-data";
	}

    @GetMapping(value="/add-marathon")
	public String addNewCar(Marathon marathon) {
		return "add-marathon";
	}
	
	@PostMapping(value="/add-marathon")
	public String addNewCarPost(@Valid Marathon marathon, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			return "add-marathon";
		
		organizerServiceImpl.insertNewMarathon(marathon);
		return "redirect:/u/marathon-view";
	}

}
