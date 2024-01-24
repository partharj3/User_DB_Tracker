package com.jsp.ums.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomerUserDetailService userDetailsService;
	
	/** 
	 * 
	 *  BCrypt Algorithm Interface, 
	 *  this feature is provided by Spring Security to Encode the field. 
	 *  Not only the Password 
	 *  
	 *  **/
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
	// auth.requestMatchers("/**") for accepting all endpoints
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())              
				   .authorizeHttpRequests(auth -> auth.requestMatchers("/users/register").permitAll().anyRequest().authenticated()) 
				   .formLogin(Customizer.withDefaults()) // withDefa uts() method gives the same fields which are already in Use.
				   .build();
	}
	
	@Bean	
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
}
