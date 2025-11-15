package com.insurancesystem.service;

import com.insurancesystem.entity.Login;

/**
 * @author Viraj Akte
 * @since 2025-10-20
 */
public interface AuthService {
	Login authenticateUser(String email, String password);

	boolean logoutUser(String token);
}
