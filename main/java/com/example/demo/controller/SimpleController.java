package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.services.UserServiceIMPL;

@Controller
public class SimpleController {
	@Autowired
	UserServiceIMPL userServiceImpl;
	
	@GetMapping(value="/marathon-view")
	public String marathonView(Model model) {
		model.addAttribute("allMarathons", userServiceImpl.findAllMarathons());
		return "marathon-view";
	}
}
