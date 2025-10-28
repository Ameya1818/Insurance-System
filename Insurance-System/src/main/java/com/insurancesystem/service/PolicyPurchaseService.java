package com.insurancesystem.service;

import java.time.LocalDate;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.insurancesystem.repository.PolicyCategoryRepository;
import com.insurancesystem.repository.PolicyPurchaseRepository;
import com.insurancesystem.repository.UserRegistrationRepository;



@Service
public class PolicyPurchaseService {

	 private final PolicyPurchaseRepository purchaseRepo;
	 private final UserRegistrationRepository userRepo;
	 private final PolicyCategoryRepository policyRepo;

	 public PolicyPurchaseService(PolicyPurchaseRepository purchaseRepo, UserRegistrationRepository userRepo, PolicyCategoryRepository policyRepo) {
	        this.purchaseRepo = purchaseRepo;
	        this.userRepo = userRepo;
	        this.policyRepo = policyRepo;
	    }
	 
	
}
