package com.insurancesystem.entity;

/**
 * Handles secure login using Spring Security and JWT.
 * After login, a token is generated for accessing other APIs safely.
 *
 * @author 
 *     Viraj Akte
 * @since 
 *     2025-10-20
 */

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "login")
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loginId;

	// Many login entries can belong to a single user.
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "userId")
	private UserRegistration user;

	@Column(nullable = false, length = 512)
	private String token;

	private LocalDateTime issuedAt;
	private LocalDateTime expiresAt;

	
	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	public UserRegistration getUser() {
		return user;
	}

	public void setUser(UserRegistration user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(LocalDateTime issuedAt) {
		this.issuedAt = issuedAt;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}
}
