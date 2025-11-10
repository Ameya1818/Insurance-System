package com.insurancesystem.service;

import com.insurancesystem.entity.Profile;
/**
 * @author Viraj Akte
 * @since 2025-10-20
 */

public interface ProfileService {

	// Get profile details 
	Profile viewProfile(Long userId);

	// Update profile details
	Profile updateProfile(Long userId, Profile updatedProfile);

}
