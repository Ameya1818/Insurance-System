package com.insurancesystem.service;
/**
 * @author Nikita Mahajan
 * @since 2025-10-27
 * @description Service interface for defining business operations related to Sms.
 */
import com.insurancesystem.entity.SMS;

public interface SMSService {
	
	public SMS sendSms(Long userId,String mobileNumber,String messageTesxt);

}
