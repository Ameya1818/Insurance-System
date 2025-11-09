package com.insurancesystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurancesystem.entity.Profile;
import com.insurancesystem.entity.UserRegistration;
/**
 * @author Viraj Akte
 * @since 2025-10-20
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {

	// Find profile based on user (each user has one profile)
	Optional<Profile> findByUser(UserRegistration user);

}
