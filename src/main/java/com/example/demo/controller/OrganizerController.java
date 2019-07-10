package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Organizer;
import com.example.demo.services.OrganizerServiceImpl;

@Controller
@RequestMapping(value = "/a")
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

}

