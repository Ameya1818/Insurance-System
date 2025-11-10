package com.insurancesystem.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.insurancesystem.entity.Profile;
import com.insurancesystem.service.ProfileService;
/**
 * @author Viraj Akte
 * @since 2025-10-20
 */

@RestController
@RequestMapping("/api/users/profile")
public class ProfileRestController {

	@Autowired
	private ProfileService profileService;

	// View existing profile details for a user
	@GetMapping("/{userId}")
	public ResponseEntity<Profile> viewProfile(@PathVariable Long userId) {
		Profile profile = profileService.viewProfile(userId);
		return ResponseEntity.ok(profile);
	}

	// Update existing profile details for a user
	@PutMapping("/{userId}")
	public ResponseEntity<Profile> updateProfile(@PathVariable Long userId, @RequestBody Profile updatedProfile) {
		Profile profile = profileService.updateProfile(userId, updatedProfile);
		return ResponseEntity.ok(profile);
	}
}
