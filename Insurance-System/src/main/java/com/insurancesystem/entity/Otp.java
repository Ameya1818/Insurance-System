package com.insurancesystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Otp {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Long otpId;
private Long userId;
private String otpCode;
private LocalDateTime expiryTime;
public Long getOtpId() {
	return otpId;
}
public void setOtpId(Long otpId) {
	this.otpId = otpId;
}
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
public String getOtpCode() {
	return otpCode;
}
public void setOtpCode(String otpCode) {
	this.otpCode = otpCode;
}
public LocalDateTime getExpiryTime() {
	return expiryTime;
}
public void setExpiryTime(LocalDateTime expiryTime) {
	this.expiryTime = expiryTime;
}
@Override
public String toString() {
	return "Otp [otpId=" + otpId + ", userId=" + userId + ", otpCode=" + otpCode + ", expiryTime=" + expiryTime + "]";
}


}
