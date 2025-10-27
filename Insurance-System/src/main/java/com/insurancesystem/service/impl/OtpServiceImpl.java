package com.insurancesystem.service.impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurancesystem.entity.Otp;
import com.insurancesystem.repository.OtpRepositiory;
import com.insurancesystem.service.EmailService;
import com.insurancesystem.service.OtpService;

@Service
public class OtpServiceImpl implements OtpService{
@Autowired
	private OtpRepositiory otprepo;
@Autowired
private EmailService emailservice;
	@Override
	public String generateOtp(Long userId, String email) {
		String otpCode = String.format("%06d", new Random().nextInt(999999));
		Otp otp = new Otp();
		otp.setUserId(userId);
		otp.setOtpCode(otpCode);
		otp.setExpiryTime(LocalDateTime.now().plusMinutes(5));
		otprepo.save(otp);
		emailservice.sedndOtpEmail(email, otpCode);
		
			 return "OTP sent successfully to " + email;
	}
	@Override
	public String verifyOtp(Long userId, String otpCode) {
		// TODO Auto-generated method stub
	Otp otp =	otprepo.findByUserIdAndOtpCode(userId, otpCode);
	if(otp.getExpiryTime().isAfter(LocalDateTime.now())) {
		 return "OTP verified successfully!";    	
	}
	else {
		return "Otp Expired!";
	}
	              
		 
	}

}
