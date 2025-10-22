package com.insurancesystem.service;

import org.springframework.stereotype.Service;

@Service
public interface OtpService {

    public String generateOtp(Long userId, String email) ;
    public String verifyOtp(Long userId,String otpCode);
}
