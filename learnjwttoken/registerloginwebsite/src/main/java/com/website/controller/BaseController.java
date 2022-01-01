package com.website.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.website.model.RegistrationUser;
import com.website.model.User;
import com.website.response.GenericResponse;
import com.website.service.RegisterService;

@Controller
public class BaseController {

	@Autowired 
	private RegisterService registerService;
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("title","Umar's Website");
		
		return "index";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("title","Umar's Website");
		model.addAttribute("registrationUser", new RegistrationUser());
		return "register";
	}
	
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("title","Umar's Website");
		model.addAttribute("user", new User());
		return "login";
	}
	
	@GetMapping("/contactus")
	public String contactus(Model model) {
		model.addAttribute("title","Umar's Website");
		
		return "contactus";
	}
	
	@PostMapping("/registeruser")
	public String registerUser(@Valid @ModelAttribute("registrationUser") RegistrationUser registrationUser,BindingResult result,Model model){
		
		if(result.hasErrors()) {
		     model.addAttribute("errors",result.getAllErrors());
		    
		     return "register";
		   }
		
		
		try {
			GenericResponse registerResponse = registerService.registerUser(registrationUser);
			if(registerResponse.getResponseCode().equals("201")) {
				model.addAttribute("registrationSuccessMessage","User was Successfully Registered");
			} else {
				model.addAttribute("registrationErrorMessage", "Please try again later.");
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "register";
	}
	
	@PostMapping("/loginuser")
	public String loginUser(@Valid @ModelAttribute("user") User user,BindingResult result,Model model) {
		if(result.hasErrors()) {
		     model.addAttribute("errors",result.getAllErrors());
		     return "login";
		   }
		try {
			GenericResponse loginResponse = registerService.loginUser(user);
			if(loginResponse.getResponseCode().equals("200")) {
				model.addAttribute("loginSuccessMessage","Successfully logged in");
				return "user/home";
			} else {
				model.addAttribute("loginErrorMessage", "Please try again later.");
				return "login";
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "login";
		
	}

	
}
