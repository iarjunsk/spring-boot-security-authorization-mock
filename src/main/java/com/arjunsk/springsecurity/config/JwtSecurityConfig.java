package com.arjunsk.springsecurity.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.arjunsk.springsecurity.security.JwtAuthenticationFilter;
import com.arjunsk.springsecurity.security.JwtAuthenticationHandlerUnauthorized;
import com.arjunsk.springsecurity.security.JwtAuthenticationProvider;
import com.arjunsk.springsecurity.security.JwtAuthenticationHandlerSuccess;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
	securedEnabled = true,
	prePostEnabled = true
)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
    private JwtAuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtAuthenticationHandlerUnauthorized unauthorizedHandler;
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean()  {
		return new ProviderManager(Collections.singletonList(authenticationProvider));
	}
	 
	@Bean
	public JwtAuthenticationFilter authenticationTokenFilterBean()  {
		JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(new JwtAuthenticationHandlerSuccess());
		return filter;
	}
	
	
	
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
			.csrf()
				.disable()
			.exceptionHandling()
				.authenticationEntryPoint(this.unauthorizedHandler)
				.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.authorizeRequests()
				.antMatchers("/auth/**")
					.permitAll()
				.antMatchers("/user/checkUsernameAvailability", "/user/checkEmailAvailability")
					.permitAll()
				.anyRequest()
					.authenticated()
				.and()
			.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
			.cors();
	}

	
	

}