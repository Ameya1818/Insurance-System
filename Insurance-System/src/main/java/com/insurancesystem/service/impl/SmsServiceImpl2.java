package com.insurancesystem.service.impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.insurancesystem.entity.SMS2;
import com.insurancesystem.exception.UserIdNotFoundException;
import com.insurancesystem.repository.SMSRepository2;
import com.insurancesystem.repository.UserRepository;
import com.insurancesystem.service.SMSService2;

@Service
@Primary
public class SmsServiceImpl2 implements SMSService2 {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SMSRepository2 smsRepository2;

    // 🔑 MSG91 Configuration (replace with real values)
    private static final String AUTH_KEY = "475661ALmYcCrscDu369044eeaP1";
    private static final String TEMPLATE_ID = "1207167890123456789";
    private static final String BASE_URL = "https://control.msg91.com/api/v5/flow/";
    private static final String SENDER_ID = "MSGIND"; // use your sender ID from MSG91

    @Override
    public SMS2 sendSms(Long userId, String mobileNumber, String messageText) {

        // ✅ Step 1: Validate user
        if (!userRepository.existsById(userId)) {
            throw new UserIdNotFoundException("Invalid userId: " + userId);
        }

        // ✅ Step 2: Ensure mobile number includes country code
		/*
		 * if (!mobileNumber.startsWith("91")) { mobileNumber = "91" + mobileNumber; }
		 */

        // ✅ Step 3: Generate 6-digit OTP
        String otp = generateOtp();

        // ✅ Step 4: Prepare REST call
        RestTemplate restTemplate = new RestTemplate();
        String status = "FAILED";
        String responseBody = "";

        try {
            // Build JSON payload for MSG91 Flow API
            String payload = "{"
                    + "\"flow_id\": \"" + TEMPLATE_ID + "\","
                    + "\"sender\": \"" + SENDER_ID + "\","
                    + "\"mobiles\": \"" + mobileNumber + "\","
                    + "\"otp\": \"" + otp + "\""
                    + "}";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("authkey", AUTH_KEY);

            HttpEntity<String> entity = new HttpEntity<>(payload, headers);

            // ✅ Step 5: Send POST request to MSG91
            ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                status = "SENT";
                responseBody = response.getBody();
            } else {
                status = "FAILED";
                responseBody = "Failed with HTTP code: " + response.getStatusCode();
            }

        } catch (Exception e) {
            responseBody = "Error: " + e.getMessage();
            e.printStackTrace();
        }

        // ✅ Step 6: Save SMS record in DB
        SMS2 sms = new SMS2();
        sms.setUserId(userId);
        sms.setMobileNumber(mobileNumber);
        sms.setMessage("Your OTP is: " + otp);
        sms.setStatus(status);
        sms.setSentDate(LocalDateTime.now());

        smsRepository2.save(sms);

        System.out.println("Generated OTP: " + otp);
        System.out.println("MSG91 Response: " + responseBody);

        return sms;
    }

    // 🔹 Utility method to generate a 6-digit OTP
    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // 6-digit random number
        return String.valueOf(otp);
    }
}
