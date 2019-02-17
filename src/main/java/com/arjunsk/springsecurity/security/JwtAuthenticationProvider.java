package com.arjunsk.springsecurity.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.arjunsk.springsecurity.security.model.JwtAuthenticationToken;
import com.arjunsk.springsecurity.security.model.User;
import com.arjunsk.springsecurity.security.model.AuthenticatedUser;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private JwtTokenUtil jwtUtil;

	@Override
	public boolean supports(Class<?> aClass) {
		return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
	}
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
												  UsernamePasswordAuthenticationToken authentication) 
												  throws AuthenticationException {

	}
	
	//normal {User} get converted to {AuthorizedUser} (with JWT token) 
	@Override
	protected UserDetails retrieveUser(String username,
									   UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) 
									   throws AuthenticationException {

		JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
		String token = jwtAuthenticationToken.getToken();

		User parsedUser = jwtUtil.parse(token);

		if (parsedUser == null) {
			throw new RuntimeException("JWT Token is incorrect");
		}

		List<GrantedAuthority> authorityList  = AuthorityUtils.commaSeparatedStringToAuthorityList(parsedUser.getRole());

		return new AuthenticatedUser(parsedUser.getId(),parsedUser.getUserName(), token, authorityList);

	}


}
