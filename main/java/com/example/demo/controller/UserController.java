package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.services.UserServiceIMPL;


import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;

@Controller
public class UserController {
	
	@Autowired
	UserServiceIMPL userServiceImpl;
	
	@GetMapping(value="/u/marathon-view")
	public String marathonView(Model model) {
		model.addAttribute("allMarathons", userServiceImpl.findAllMarathons());
		return "marathon-view";
	}
	
	@GetMapping(value="/signup")
	public String registerUserGet(User user) {
		return "signup";
	}
	
	@PostMapping(value = "/signup")
	public String registerUserPost(@Valid User user, BindingResult result) {
		System.out.println(user.toString());
		if(result.hasErrors()) 
			return "signup";
		else {
			userServiceImpl.addNewUser(user);
			return "redirect:/authpage";
		}
	}
	
	@GetMapping(value="/authpage")
	public String authoriseUserGet(User user) {
		return "authpage";
	}
	
	@PostMapping(value = "/authpage")
	public String authoriseUserPost(@Valid User user, BindingResult result) {
		
		return "redirect:/isOK";
	}
	
	@GetMapping(value="/isOK")
	public String isOK() {
		return "isOK";
	}
}
