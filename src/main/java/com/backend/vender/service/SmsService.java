package com.backend.vender.service;

public interface SmsService {
    public void sendOtp(String phoneNumber,String otp);
}
