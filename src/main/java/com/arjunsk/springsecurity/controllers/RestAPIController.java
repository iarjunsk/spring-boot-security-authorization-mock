package com.arjunsk.springsecurity.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjunsk.springsecurity.DTO.ApiResponseDTO;
import com.arjunsk.springsecurity.DTO.AppResponseDTO;

@CrossOrigin
@RestController
@RequestMapping("/rest")
public class RestAPIController {
	
	private final Logger logger = LoggerFactory.getLogger(RestAPIController.class);
	
	@PreAuthorize("hasRole('USER')")	
	@GetMapping("/getApps")
	public ResponseEntity<List<?>> getApps(@RequestHeader("Authorization") String header){
		logger.info(header);
		
		List<AppResponseDTO> appList = new ArrayList<>();
		appList.add(new AppResponseDTO("app_id",10L,"/userlogin"));
		
		return new ResponseEntity<>(appList, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')")	
	@GetMapping("/getApis")
	public ResponseEntity<List<?>> getApis(@RequestHeader("Authorization") String header){
		logger.info(header);
		
		List<ApiResponseDTO> apiList = new ArrayList<>();
		apiList.add(new ApiResponseDTO("arjunsk.com","app_id","ROLE_ADMIN","/POST","button-id","test-ui"));
		
		
		return new ResponseEntity<>(apiList, HttpStatus.OK);
	}
	
	
	
}
