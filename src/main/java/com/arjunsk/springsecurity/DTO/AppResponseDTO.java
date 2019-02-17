package com.arjunsk.springsecurity.DTO;

import lombok.Data;

@Data
public class AppResponseDTO {
	
	private String appId;
	private Long expiryTime;
	private String callbackURL;
	 
	public AppResponseDTO(String appId, Long expiryTime, String callbackURL) {
		super();
		this.appId = appId;
		this.expiryTime = expiryTime;
		this.callbackURL = callbackURL;
	}
	 
	 
}
