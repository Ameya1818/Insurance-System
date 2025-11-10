package com.insurancesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurancesystem.entity.SMS2;
/**
 * @author Nikita Mahajan
 * @since 2025-10-27
 * @description Repository for performing database operations on Sms.
 */
public interface SMSRepository2 extends JpaRepository<SMS2, Long>{
	
	

}
