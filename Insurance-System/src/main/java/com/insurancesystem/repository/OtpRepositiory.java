package com.insurancesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurancesystem.entity.Otp;
@Repository 
public interface OtpRepositiory extends JpaRepository<Otp, Long>{
public Otp findByUserIdAndOtpCode (Long userId, String otpCode);
}
