package com.insurancesystem.restcontroller;

/**
 * @author Nikita
 * @since 2025-10-27
 * @description Controller handling API requests related to sending SMS using MSG91.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.insurancesystem.entity.SMS;
import com.insurancesystem.entity.SMS2;
import com.insurancesystem.service.SMSService;
import com.insurancesystem.service.SMSService2;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sms")
public class SMSRestController2 {

    @Autowired
    private SMSService2 smsService2;

    /**
     * Sends an SMS using MSG91 Gateway.
     * 
     * Example request (JSON):
     * {
     *   "userId": 1,
     *   "mobileNumber": "9876543210",
     *   "message": "Your OTP is 123456"
     * }
     */
    @PostMapping("/send")
    public ResponseEntity<SMS2> sendSms(@Valid @RequestBody SMS2 smsRequest) {
        SMS2 response = smsService2.sendSms(
                smsRequest.getUserId(),
                smsRequest.getMobileNumber(),
                smsRequest.getMessage());

        return ResponseEntity.ok(response);
    }
}
