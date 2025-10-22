package com.insurancesystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.insurancesystem.service.EmailService;
@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender mailsender;
	
	@Override
	public void sedndOtpEmail(String toEmail, String otpCode) {
	SimpleMailMessage message = new SimpleMailMessage();
	message.setTo(toEmail);
	//message.setFrom("yogeshsunilsonawane");
	message.setSubject("Password Reset Otp");
	message.setText("Your OTP for password reset is: " + otpCode);
		mailsender.send(message);
	}

}
