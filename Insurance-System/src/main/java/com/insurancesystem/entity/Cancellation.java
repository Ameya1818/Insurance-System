package com.insurancesystem.entity;

import jakarta.persistence.*;
import java.time.LocalDate;


/**
 * The entity represents a record of a cancelled insurance policy.
 * It stores information such as the cancellation date, refund amount, 
 * and the associated policy purchase reference.
 *
 * @author Ameya Shingane
 * @version 1.0
 * 
 */

@Entity
@Table(name = "cancellation")
public class Cancellation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cancelId;

	@ManyToOne
	@JoinColumn(name = "purchase_id")
	private PolicyPurchase purchase;
	private LocalDate cancellationDate;
	private Double refundAmount;

	// Getters and Setters
	public Long getCancelId() {
		return cancelId;
	}

	public void setCancelId(Long cancelId) {
		this.cancelId = cancelId;
	}

	public PolicyPurchase getPurchase() {
		return purchase;
	}

	public void setPurchase(PolicyPurchase purchase) {
		this.purchase = purchase;
	}

	public LocalDate getCancellationDate() {
		return cancellationDate;
	}

	public void setCancellationDate(LocalDate cancellationDate) {
		this.cancellationDate = cancellationDate;
	}

	public Double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}
}
