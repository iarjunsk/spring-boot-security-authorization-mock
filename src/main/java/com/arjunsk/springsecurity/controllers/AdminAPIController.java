package com.arjunsk.springsecurity.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjunsk.springsecurity.DTO.UserResponseDTO;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminAPIController {

	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")	
	@GetMapping("/getUsers")
	public ResponseEntity<?> getUsers(){
		
		List<UserResponseDTO> userList = new ArrayList<>();
		userList.add(new UserResponseDTO("arjunsk15","appId","ADMIN",true));
		
		return new ResponseEntity<>(userList, HttpStatus.OK);

	}
}
