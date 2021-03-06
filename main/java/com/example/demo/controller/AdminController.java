package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Marathon;
import com.example.demo.model.Organizer;
import com.example.demo.services.AdminServiceIMPL;

@Controller
@RequestMapping(value = "/a")
public class AdminController {
	
	@Autowired
	AdminServiceIMPL adminServiceImpl;
	
	@GetMapping(value = "/view-org")
	public String vieworg(Model model) {
		model.addAttribute("object", adminServiceImpl.selectAll());
	return "view-org";
	}
	
	@GetMapping(value = "/add-org")
	public String addorgGet(Organizer organizer)
	{
		return "add-org";
	}
	
	@PostMapping(value = "/add-org")
	public String addorgPost(@Valid Organizer organizer, BindingResult result)
	{
		if(result.hasErrors())
			return "add-org";
		else
		{
			adminServiceImpl.addNewOrganizer(organizer);
		return "redirect:/a/view-org";
		}
	}
	@GetMapping(value = "/update-org/{id_org}")
	public String updateorgGet(@PathVariable(name ="id_org") long id_org, Model model) {
		model.addAttribute("organizer", adminServiceImpl.selectById_org(id_org));
		return "update-org";
	}
	@PostMapping(value = "/update-org/{id_org}")
	public String updateorgPost(@PathVariable(name="id_org") long id_org, Organizer organizer)
	{
		adminServiceImpl.updateOrganizerById_org(organizer, id_org);
		return "redirect:/a/view-org";
	}
	@GetMapping(value = "/delete/{id_org}")
	public String deleteorgGet(@PathVariable(name="id_org") long id_org) {
		adminServiceImpl.deleteOrganizerById_org(id_org);
		return "redirect:/a/view-org";
	
	}
	
	
	@GetMapping(value="/add-marathon")
	public String addNewCar(Marathon marathon) {
		return "add-marathon";
	}
}