package com.insurancesystem.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurancesystem.entity.Profile;
import com.insurancesystem.entity.UserRegistration;
import com.insurancesystem.exception.ResourceNotFoundException;
import com.insurancesystem.repository.ProfileRepository;
import com.insurancesystem.repository.UserRegistrationRepository;
import com.insurancesystem.service.ProfileService;

/**
 * @author Viraj Akte
 * @since 2025-10-20
 */
@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository profileRepo;

	@Autowired
	private UserRegistrationRepository userRepo;

	@Override
	public Profile viewProfile(Long userId) {
		// Find user from UserRegistration table
		UserRegistration user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		// Try to find existing profile
		Optional<Profile> existingProfile = profileRepo.findByUser(user);

		// If not found, create one automatically with default "Not Provided" values
		if (existingProfile.isEmpty()) {
			Profile newProfile = new Profile();
			newProfile.setUser(user);
			newProfile.setAddress("Not Provided");
			newProfile.setCity("Not Provided");
			newProfile.setState("Not Provided");
			newProfile.setPinCode("Not Provided");

			Profile savedProfile = profileRepo.save(newProfile);
			return savedProfile;
		}

		return existingProfile.get();
	}

	@Override
	public Profile updateProfile(Long userId, Profile updatedProfile) {
		// Find the user first
		UserRegistration user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		// Find or auto-create the profile if it doesn’t exist yet
		Profile existingProfile = profileRepo.findByUser(user).orElseGet(() -> {
			Profile newProfile = new Profile();
			newProfile.setUser(user);
			newProfile.setAddress("Not Provided");
			newProfile.setCity("Not Provided");
			newProfile.setState("Not Provided");
			newProfile.setPinCode("Not Provided");
			return profileRepo.save(newProfile);
		});

		// Update fields (only if user provided new ones)
		existingProfile.setAddress(
				updatedProfile.getAddress() != null ? updatedProfile.getAddress() : existingProfile.getAddress());
		existingProfile
				.setCity(updatedProfile.getCity() != null ? updatedProfile.getCity() : existingProfile.getCity());
		existingProfile
				.setState(updatedProfile.getState() != null ? updatedProfile.getState() : existingProfile.getState());
		existingProfile.setPinCode(
				updatedProfile.getPinCode() != null ? updatedProfile.getPinCode() : existingProfile.getPinCode());

		return profileRepo.save(existingProfile);
	}

}