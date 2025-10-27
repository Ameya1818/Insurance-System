package com.insurancesystem.service.impl;


import com.insurancesystem.entity.*;
import com.insurancesystem.repository.PolicyPurchaseRepository;
import com.insurancesystem.service.CancellationService;
import com.insurancesystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
        long totalDays = ChronoUnit.DAYS.between(purchase.getPolicyStartDate(), purchase.getPolicyEndDate());
        long remainingDays = ChronoUnit.DAYS.between(LocalDate.now(), purchase.getPolicyEndDate());

        double refund = (purchase.getPremiumAmount() * remainingDays) / totalDays;

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
