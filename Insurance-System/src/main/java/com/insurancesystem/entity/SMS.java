package com.insurancesystem.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//Integrate mock SMS sender for important alerts like payment confirmation or OTP.
/**
 * @author Nikita Mahajan
 * @since 2025-10-27
 * @description This class handles SMS entities.
 */
@Entity
@Table(name = "sms")
public class SMS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long smsId;
	
	@NotNull(message = "user id cannot be null")
	private Long userId;
	
	@NotBlank(message = "Mobile number is required")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number format")
	private String mobileNumber;

	@NotBlank(message = "Message text cannot be blank")
	@Size(max = 255, message = "Message text must not exceed 255 characters")
	private String message;

	//@NotBlank(message = "Status cannot be blank")
	@Pattern(regexp = "SENT|FAILED", message = "Status must be either 'SENT' or 'FAILED'")
	private String status;
	
	private LocalDateTime sentDate;

	// Constructors
	public SMS() {

	}

	public SMS(Long smsId, Long userId, String mobileNumber, String message, String status, LocalDateTime sentDate) {
		this.smsId = smsId;
		this.userId = userId;
		this.mobileNumber = mobileNumber;
		this.message = message;
		this.status = status;
		this.sentDate = sentDate;
	}
	// setters and getters

	public Long getSmsId() {
		return smsId;
	}

	public void setSmsId(Long smsId) {
		this.smsId = smsId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getSentDate() {
		return sentDate;
	}

	public void setSentDate(LocalDateTime sentDate) {
		this.sentDate = sentDate;
	}

	@Override
	public String toString() {
		return "SMS [smsId=" + smsId + ", userId=" + userId + ", mobileNumber=" + mobileNumber + ", message=" + message
				+ ", status=" + status + ", sentDate=" + sentDate + "]";
	}

}
