package com.arjunsk.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arjunsk.springsecurity.security.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	@Autowired
	JwtConfig jwtConfig;

    public String generate(User user) {

        Claims claims = Jwts.claims().setSubject(user.getUserName());
        claims.put("userId", String.valueOf(user.getId()));
        claims.put("role", user.getRole());
        
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512,jwtConfig.getSecret())
                .compact();
        
//        Authentication auth;
//        Long now = System.currentTimeMillis();
//        
//        return Jwts.builder()
//    			.setSubject(auth.getName())	
//    			// Convert to list of strings. 
//    			// This is important because it affects the way we get them back in the Gateway.
//    			.claim("authorities", auth.getAuthorities().stream()
//    				.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
//    			.setIssuedAt(new Date(now))
//    			.setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
//    			.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
//    			.compact();
    }
    
    public User parse(String token) {

        try {
            Claims body = Jwts.parser()
		                    .setSigningKey(jwtConfig.getSecret())
		                    .parseClaimsJws(token)
		                    .getBody();

            User user = new User();

            user.setUserName(body.getSubject());
            user.setId(Long.parseLong((String) body.get("userId")));
            user.setRole((String) body.get("role"));
            
            return user;
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    
}
