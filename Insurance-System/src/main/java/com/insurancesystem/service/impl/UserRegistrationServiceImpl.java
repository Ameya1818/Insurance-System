package com.insurancesystem.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.insurancesystem.entity.UserRegistration;
import com.insurancesystem.exception.DuplicateEmailException;
import com.insurancesystem.repository.UserRegistrationRepository;
import com.insurancesystem.service.UserRegistrationService;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserRegistration userGetRegister(UserRegistration userRegistration) {
        Optional<UserRegistration> existingUser = userRegistrationRepository.findByEmail(userRegistration.getEmail());
        if (existingUser.isPresent()) {
            throw new DuplicateEmailException("User email already registered");
        }

        //  Securely hash password before saving
        String encodedPassword = passwordEncoder.encode(userRegistration.getPassword());
        userRegistration.setPassword(encodedPassword);

        return userRegistrationRepository.save(userRegistration);
    }
}
