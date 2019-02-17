package com.arjunsk.springsecurity.DTO;

import lombok.Data;

@Data
public class ApiResponseDTO {
	
	private String apiURL;
	private String appId;
	private String role;
	private String methodId;
	private String serverMetadata;
	private String uiMetadata;
	
	public ApiResponseDTO(String apiURL, String appId, String role, String methodId, String serverMetadata, String uiMetadata) {
		super();
		this.apiURL = apiURL;
		this.appId = appId;
		this.role = role;
		this.methodId = methodId;
		this.serverMetadata = serverMetadata;
		this.uiMetadata = uiMetadata;
	}
	
	
	
}
