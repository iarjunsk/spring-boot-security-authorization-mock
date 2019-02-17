package com.arjunsk.springsecurity.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserAPIController {
	
	@GetMapping("/checkUsernameAvailability")
	public String checkUsernameAvailability() {
		return "Username is available";
		//TODO: Implementation use to be done
	}
	
	
	@GetMapping("/checkEmailAvailability")
	public String checkEmailAvailability() {
		return "Email is available";
	}
}
