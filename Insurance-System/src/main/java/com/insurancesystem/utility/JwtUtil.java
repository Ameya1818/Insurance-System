package com.insurancesystem.utility;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.insurancesystem.entity.UserRegistration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * @author Viraj Akte
 * @since 2025-10-20
 */
@Component
public class JwtUtil {

	// Secret key for signing the JWT (loaded from application.properties)
	@Value("${app.jwt.secret}")
	private String secret;

	// Token validity period in milliseconds (loaded from application.properties)
	@Value("${app.jwt.expiration-ms}")
	private long jwtExpirationInMs;

	// Generates a JWT token for the given user. It includes userId, role, email,
	// issued date, and expiry date
	public String generateToken(UserRegistration user) {
		// Store custom information (claims) inside the token
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", user.getUserId());
		claims.put("role", user.getRole().getRoleName());

		Date now = new Date(); // current time
		Date expiry = new Date(now.getTime() + jwtExpirationInMs); // token expiry time

		// Create a secure key from the secret string
		SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

		// Build the token with claims, subject, issue/expiry dates, and signature
		return Jwts.builder().setClaims(claims).setSubject(user.getEmail()) // subject usually represents username/email
				.setIssuedAt(now) // issue time
				.setExpiration(expiry) // expiry time
				.signWith(key, SignatureAlgorithm.HS256) // signing the token with algorithm
				.compact(); // generate final token
	}

	// Extracts the username (email) from the given token.

	public String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(secret.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(token)
				.getBody().getSubject();
	}

	// Validates the JWT token by checking: 1. If the username (email) matches
	// 2. If the token is not expired

	public boolean validateToken(String token, String email) {
		final String username = extractUsername(token);
		return (username.equals(email) && !isTokenExpired(token));
	}

	// Checks if the token has expired.

	private boolean isTokenExpired(String token) {
		Date expiration = Jwts.parserBuilder().setSigningKey(secret.getBytes(StandardCharsets.UTF_8)).build()
				.parseClaimsJws(token).getBody().getExpiration();
		return expiration.before(new Date());
	}
}
