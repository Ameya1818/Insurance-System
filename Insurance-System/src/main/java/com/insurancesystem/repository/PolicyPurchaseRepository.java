package com.insurancesystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurancesystem.entity.Policy;
import com.insurancesystem.entity.PolicyPurchase;
import com.insurancesystem.entity.UserRegistration;

@Repository
public interface PolicyPurchaseRepository extends JpaRepository<PolicyPurchase, Long> {

	Optional<PolicyPurchase> findByUserAndPolicy(UserRegistration userRegistration, Policy policy);
}