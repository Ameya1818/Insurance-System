package com.insurancesystem.service.impl;

/**
 * @author Viraj Akte
 * @since 2025-10-20
 */
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.insurancesystem.entity.Login;
import com.insurancesystem.entity.Session;
import com.insurancesystem.entity.UserRegistration;
import com.insurancesystem.exception.InvalidCredentialsException;
import com.insurancesystem.repository.LoginRepository;
import com.insurancesystem.repository.SessionRepository;
import com.insurancesystem.repository.UserRegistrationRepository;
import com.insurancesystem.service.AuthService;
import com.insurancesystem.utility.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRegistrationRepository userRepository;

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public Login authenticateUser(String email, String password) {
		UserRegistration user = userRepository.findByEmail(email)
				.orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

		// Check if entered password matches the encrypted password in database
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new InvalidCredentialsException("Invalid email or password");
		}

		// Generate JWT token for the authenticated user
		String token = jwtUtil.generateToken(user);

		// Create a new Login record to store token and timestamp info
		Login login = new Login();
		login.setUser(user);
		login.setToken(token);
		login.setIssuedAt(LocalDateTime.now()); // current time
		login.setExpiresAt(LocalDateTime.now().plusMinutes(15)); // token valid for 15 mins

		loginRepository.save(login);

		// Save session info
		Session session = new Session();
		session.setUser(user);
		session.setToken(token);
		session.setLoginTime(LocalDateTime.now());
		session.setActive(true);
		sessionRepository.save(session);

		return login;
	}
	
	@Override
	public boolean logoutUser(String token) {
	    // Find session by token
	    Session session = sessionRepository.findByToken(token).orElse(null);

	    if (session == null || !session.isActive()) {
	        return false;
	    }

	    // Mark session as inactive and set logout time
	    session.setActive(false);
	    session.setLogoutTime(LocalDateTime.now());
	    sessionRepository.save(session);

	    return true;
	}

}
