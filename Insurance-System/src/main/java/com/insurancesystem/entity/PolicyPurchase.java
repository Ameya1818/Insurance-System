package com.insurancesystem.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.validation.constraints.*;
@Entity
@Table(name = "policy_purchases")
public class PolicyPurchase {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long purchaseId;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id", nullable = false)
	    @JsonBackReference
	    private UserRegistration user;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "policy_id", nullable = false)
	    private Policy policy;

	    @Column(nullable = false)
	    private LocalDate purchaseDate;

	    @Column(nullable = false)
	    private LocalDate policyStartDate;

	    @Column(nullable = false)
	    private LocalDate policyEndDate;

	    @NotNull(message = "Premium amount is required")
	    @Positive(message = "Premium amount must be positive")
	    private Double premiumAmount;
	    
	    private String status; // ACTIVE, CANCELLED, EXPIRED


	    // Getters and Setters
	    public Long getPurchaseId() {
	        return purchaseId;
	    }

	    public void setPurchaseId(Long purchaseId) {
	        this.purchaseId = purchaseId;
	    }

	    public UserRegistration getUser() {
	        return user;
	    }

	    public void setUser(UserRegistration user) {
	        this.user = user;
	    }

	    public Policy getPolicy() {
	        return policy;
	    }

	    public void setPolicy(Policy policy) {
	        this.policy = policy;
	    }

	    public LocalDate getPurchaseDate() {
	        return purchaseDate;
	    }

	    public void setPurchaseDate(LocalDate purchaseDate) {
	        this.purchaseDate = purchaseDate;
	    }

	    public LocalDate getPolicyStartDate() {
	        return policyStartDate;
	    }

	    public void setPolicyStartDate(LocalDate policyStartDate) {
	        this.policyStartDate = policyStartDate;
	    }

	    public LocalDate getPolicyEndDate() {
	        return policyEndDate;
	    }

	    public void setPolicyEndDate(LocalDate policyEndDate) {
	        this.policyEndDate = policyEndDate;
	    }

	    public Double getPremiumAmount() {
	        return premiumAmount;
	    }

	    public void setPremiumAmount(Double premiumAmount) {
	        this.premiumAmount = premiumAmount;
	    }
	    
	    public String getStatus() {
	        return status;
	    }
	    
	    public void setStatus(String status) {
	        this.status = status;
	    }
	}
	
