package com.arjunsk.springsecurity.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjunsk.springsecurity.DTO.AuthenticatedUserResponseDTO;
import com.arjunsk.springsecurity.DTO.CredentialDataDTO;
import com.arjunsk.springsecurity.security.JwtTokenUtil;
import com.arjunsk.springsecurity.security.model.User;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

	private final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private  JwtTokenUtil jwtUtil;
	
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody CredentialDataDTO cred){
		
		AuthenticatedUserResponseDTO auth_user = new AuthenticatedUserResponseDTO();
		logger.info(cred.getUsername()+" "+cred.getPassword());
		try {
			
			if(cred.getUsername().equals("test") && cred.getPassword().equals("test") ) {
				
				User user = new User();
				user.setUserName(cred.getUsername());
				user.setId(1);
				user.setRole("ROLE_USER");
	
				String token = jwtUtil.generate(user);
				
				auth_user.setToken(token);
				
			}else {
				throw new Exception("Invalid credential");
			}
			
		}catch(Exception ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(auth_user,HttpStatus.OK);
	
	}
	
}
