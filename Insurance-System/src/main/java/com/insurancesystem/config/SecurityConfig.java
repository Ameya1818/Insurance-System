package com.insurancesystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	@Autowired
	private JwtAuthenticationFilter jwtFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
				// Public endpoints
				.requestMatchers("/api/auth/**", "/api/register/**", "/api/notifications/**").permitAll()

				// Secured endpoints
				.requestMatchers("/viewProfile/**", "/updateProfile/**").authenticated()

				// All other requests must be authenticated
				.anyRequest().authenticated())

				// Disable session (JWT based auth)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				// Add JWT Filter before UsernamePasswordAuthenticationFilter
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	/*
	 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
	 * throws Exception { http .csrf(csrf -> csrf.disable()) // disable CSRF for
	 * testing .authorizeHttpRequests(auth -> auth .anyRequest().permitAll() // ✅
	 * allow ALL endpoints ) .headers(headers -> headers.frameOptions(frame ->
	 * frame.disable())); // allow H2 console if used
	 * 
	 * return http.build(); }
	 */

}