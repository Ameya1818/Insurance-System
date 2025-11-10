package com.insurancesystem.service.impl;

/**
 * @author Nikita Mahajan
 * @since 2025-10-27
 * @description Service implementation class that handles the business logic for Sms.
 */
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.insurancesystem.entity.SMS;
import com.insurancesystem.exception.UserIdNotFoundException;
import com.insurancesystem.repository.SMSRepository;
import com.insurancesystem.repository.UserRepository;
import com.insurancesystem.service.SMSService;

@Service
public class SmsServiceImpl implements SMSService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SMSRepository smsRepository;

	@Override
	public SMS sendSms(Long userId, String mobileNumber, String messageTesxt) {

		// Check if user exists
		if (!userRepository.existsById(userId)) {
			throw new UserIdNotFoundException("Invalid userId: " + userId);
		}
		// This is used for send mock sms instead of used any sms gateway
		boolean isSent = Math.random() > 0.1;
		String status = isSent ? "SENT" : "FAILED";

		// save sms related data into db
		SMS sms = new SMS();
		sms.setUserId(userId);
		;
		sms.setMobileNumber(mobileNumber);
		sms.setMessage(messageTesxt);
		sms.setStatus(status);
		sms.setSentDate(LocalDateTime.now());

		smsRepository.save(sms);

		System.out.println("Mock SMS Sent to " + mobileNumber + " : " + messageTesxt);
		return sms;

	}

}
