package com.arjunsk.springsecurity.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjunsk.springsecurity.security.JwtTokenProvider;
import com.arjunsk.springsecurity.security.DTO.JwtAuthenticationResponseDTO;
import com.arjunsk.springsecurity.security.DTO.LoginRequestDTO;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

	private final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;
    
    @Autowired
    PasswordEncoder passwordEncode;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest){
		
		logger.info(loginRequest.getUsername() + " "+ loginRequest.getPassword());
		
		String jwt = "";
		try {
			Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getUsername(),
	                        loginRequest.getPassword()
	                )
	        );
	
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	
	        jwt = tokenProvider.generateToken(authentication);
		}catch(Exception ex) {
			logger.info("authenticateUser() : Exception " + ex);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex);
		}
		
        return ResponseEntity.ok(new JwtAuthenticationResponseDTO(jwt));

	}
	
}
