package com.arjunsk.springsecurity.security.DTO;

import lombok.Data;

@Data
public class JwtAuthenticationResponseDTO {

    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

   
}
