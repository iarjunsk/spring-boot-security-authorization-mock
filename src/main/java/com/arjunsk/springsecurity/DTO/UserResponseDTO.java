package com.arjunsk.springsecurity.DTO;

import lombok.Data;

@Data
public class UserResponseDTO {

	private String userId;
	private String appId;
	private String role;
	private boolean grantAccess;
	
	public UserResponseDTO(String userId, String appId, String role, boolean grantAccess) {
		super();
		this.userId = userId;
		this.appId = appId;
		this.role = role;
		this.grantAccess = grantAccess;
	}

	
}
