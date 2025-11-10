package com.insurancesystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sms_logs") // ✅ Unique table name to avoid conflict with old SMS entity
public class SMS2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long smsId;

    @NotNull(message = "User ID is required")
    private Long userId;


    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number format")
    private String mobileNumber;


    @NotBlank(message = "Message text cannot be blank")
    @Column(name = "message", nullable = false, length = 255)
    private String message;

    @Column(name = "status")
    private String status;

    @Column(name = "sent_date")
    private LocalDateTime sentDate;

    // ✅ Constructors
    public SMS2() {}

    public SMS2(Long userId, String mobileNumber, String message, String status, LocalDateTime sentDate) {
        this.userId = userId;
        this.mobileNumber = mobileNumber;
        this.message = message;
        this.status = status;
        this.sentDate = sentDate;
    }

    // ✅ Getters and Setters
    public Long getSmsId() {
        return smsId;
    }

    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }
}
