package com.insurancesystem.repository;

import com.insurancesystem.entity.Policy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
	Optional<Policy> findByPolicyName(String policyName);
}