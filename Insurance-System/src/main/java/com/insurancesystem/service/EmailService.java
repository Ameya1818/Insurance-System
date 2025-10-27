package com.insurancesystem.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
public void sedndOtpEmail(String toEmail, String otpCode);
}
