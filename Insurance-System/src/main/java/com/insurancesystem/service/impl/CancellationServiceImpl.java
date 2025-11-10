package com.insurancesystem.service.impl;


import com.insurancesystem.entity.*;
import com.insurancesystem.repository.PolicyPurchaseRepository;
import com.insurancesystem.service.CancellationService;
import com.insurancesystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


/*
 * Implementation of the CancellationService interface.
 * This service handles the logic for policy cancellation including:
 * - Checking if a policy is expired or already cancelled.
 * - Calculating the refund based on remaining policy days.
 * - Creating a record in the cancellation table.
 * - Updating the policy purchase status to CANCELLED.
 *
 * @author Ameya Shingane
 * @version 1.0
 * 
 */

@Service
public class CancellationServiceImpl implements CancellationService  {

    @Autowired
    private PolicyPurchaseRepository purchaseRepository;

    @Autowired
    private CancellationRepository cancellationRepository;

    @Override
    public String cancelPolicy(Long purchaseId) {
        PolicyPurchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new RuntimeException("Purchase not found"));

        // Check expired policy
        if (purchase.getPolicyEndDate().isBefore(LocalDate.now())) {
            return "Expired policies cannot be cancelled.";
        }

        // Check if already cancelled
        if ("CANCELLED".equalsIgnoreCase(purchase.getStatus())) {
            return "Policy already cancelled.";
        }

        // Calculate refund based on remaining days
        long totalDays = ChronoUnit.DAYS.between(purchase.getPolicyStartDate(), purchase.getPolicyEndDate());//Calculate total policy duration
        long remainingDays = ChronoUnit.DAYS.between(LocalDate.now(), purchase.getPolicyEndDate());//Calculate remaining days

        double refund = (purchase.getPremiumAmount() * remainingDays) / totalDays;//Calculate refund

        // Create cancellation record
        Cancellation cancellation = new Cancellation();
        cancellation.setPurchase(purchase);
        cancellation.setCancellationDate(LocalDate.now());
        cancellation.setRefundAmount(refund);
        cancellationRepository.save(cancellation);

        // Update purchase status
        purchase.setStatus("CANCELLED");
        purchaseRepository.save(purchase);

        return "Policy cancelled successfully. Refund: ₹" + refund;
    }
}
