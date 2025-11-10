package com.insurancesystem.restcontroller;
/**
 * @author Nikita Mahajan
 * @since 2025-10-27
 * @description Controller handling API requests related to Sms.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurancesystem.entity.SMS;
import com.insurancesystem.service.SMSService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sms")
public class SMSRestController {

	@Autowired
	private SMSService smsService;

	
	@PostMapping("/send")
	public SMS sendSms(@Valid @RequestBody SMS smsRequest) {
		return smsService.sendSms(smsRequest.getUserId(), 
				smsRequest.getMobileNumber(), 
				smsRequest.getMessage());
		//return "Mock SMS processed Successfully";

	}

}
