package com.insurancesystem.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

/**
 * Represents a user session for tracking login and logout activity. Used for
 * auditing and managing JWT-based sessions.
 *
 * @author Viraj Akte
 * @since 2025-11-05
 */
@Entity
@Table(name = "session")
public class Session {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sessionId;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
	private UserRegistration user;

	@Column(nullable = false, length = 512)
	private String token;

	private LocalDateTime loginTime;
	private LocalDateTime logoutTime;

	private boolean isActive; // true when user is logged in, false when logged out

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
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

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

	public LocalDateTime getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(LocalDateTime logoutTime) {
		this.logoutTime = logoutTime;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}
}
