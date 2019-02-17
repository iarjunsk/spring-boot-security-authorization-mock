package com.arjunsk.springsecurity.security.DTO;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequestDTO {
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	
}
