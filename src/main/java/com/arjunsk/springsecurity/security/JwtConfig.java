package com.arjunsk.springsecurity.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@Data
public class JwtConfig {


    @Value("${app.jwt.header}")
    private String header;

    @Value("${app.jwt.prefix}")
    private String prefix;
    
    @Value("${app.jwt.secret}")
    private String secret;
    
    @Value("${app.jwt.expirationInMs}")
    private int expirationInMs;    
}