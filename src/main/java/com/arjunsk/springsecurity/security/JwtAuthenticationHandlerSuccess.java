package com.arjunsk.springsecurity.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class JwtAuthenticationHandlerSuccess implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, 
										HttpServletResponse httpServletResponse,
										Authentication authentication) 
										throws IOException, ServletException {
		
		System.out.println("Successfully Authentication");
		// You could send a mail to the user's email etc
	}

}
