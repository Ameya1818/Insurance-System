package com.insurancesystem.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.insurancesystem.entity.Login;
import com.insurancesystem.service.AuthService;

/**
 * @author Viraj Akte
 * @since 2025-10-20
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	public static class LoginRequest {
		public String email;
		public String password;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		Login login = authService.authenticateUser(request.email, request.password);

		// Create a response map to send token and login info back to client
		Map<String, Object> response = new HashMap<>();
		response.put("loginId", login.getLoginId());
		response.put("userId", login.getUser().getUserId());
		response.put("token", login.getToken());
		response.put("issuedAt", login.getIssuedAt());
		response.put("expiresAt", login.getExpiresAt());

		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	        return ResponseEntity.status(401).body(Map.of("error", "Missing or invalid token"));
	    }

	    String token = authHeader.substring(7);

	    boolean isLoggedOut = authService.logoutUser(token);
	    if (isLoggedOut) {
	        return ResponseEntity.ok(Map.of("message", "Logout successful"));
	    } else {
	        return ResponseEntity.status(400).body(Map.of("error", "Invalid or already logged-out token"));
	    }
	}

}
