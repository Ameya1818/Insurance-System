package com.insurancesystem.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.insurancesystem.entity.UserRegistration;
import com.insurancesystem.repository.UserRegistrationRepository;
import com.insurancesystem.utility.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This filter runs once for every request.
 * It checks the JWT token from the header, validates it,
 * and sets authentication if the token is valid.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRegistrationRepository userRepository;

	// Skip token validation for login and registration endpoints
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		String path = request.getServletPath();
		return path.startsWith("/api/auth/") || path.equals("/api/register") || path.startsWith("/api/register/");
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Allow CORS preflight requests to pass without checking token
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			filterChain.doFilter(request, response);
			return;
		}

		// Read Authorization header from the request
		final String header = request.getHeader("Authorization");
		String email = null;
		String token = null;

		// If header is missing or doesn’t start with "Bearer ", return 401
		if (header == null || !header.startsWith("Bearer ")) {
			writeUnauthorized(response, "Invalid or missing token");
			return;
		}

		// Remove "Bearer " prefix to get the actual token
		token = header.substring(7);

		try {
			// Extract email (username) from token
			email = jwtUtil.extractUsername(token);
		} catch (ExpiredJwtException e) {
			writeUnauthorized(response, "JWT token expired");
			return;
		} catch (SignatureException e) {
			writeUnauthorized(response, "Invalid JWT signature");
			return;
		} catch (IllegalArgumentException e) {
			writeUnauthorized(response, "Invalid token");
			return;
		} catch (Exception e) {
			writeUnauthorized(response, "Token parsing error");
			return;
		}

		// If email is found and user is not already authenticated
		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			// Find user in database using email
			UserRegistration user = userRepository.findByEmail(email).orElse(null);

			// If user not found, return 401
			if (user == null) {
				writeUnauthorized(response, "User not found for token");
				return;
			}

			// Validate the token for the user
			if (jwtUtil.validateToken(token, email)) {
				// Create authentication object
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						new User(email, "", java.util.Collections.emptyList()), null,
						java.util.Collections.emptyList());

				// Add details and set authentication in context
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);

				// Continue with next filter or controller
				filterChain.doFilter(request, response);
				return;
			} else {
				writeUnauthorized(response, "Invalid or expired token");
				return;
			}
		}

		// If authentication fails or token is missing
		writeUnauthorized(response, "Unauthorized");
	}

	// Helper method to send 401 Unauthorized JSON response
	private void writeUnauthorized(HttpServletResponse response, String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("{\"error\":\"" + message + "\"}");
	}
}
