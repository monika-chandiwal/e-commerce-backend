package com.backend.vender.service;

public interface OtpService {
    public String generateOtp(String phoneNumber);
    public void sendOtp(String phoneNumber);
    public boolean verifyOtp(String phoneNumber, String code);
}
