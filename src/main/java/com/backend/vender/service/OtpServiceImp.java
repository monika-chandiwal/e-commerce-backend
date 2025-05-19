package com.backend.vender.service;

public class OtpServiceImp implements OtpService{

    @Override
    public String generateOtp(String phoneNumber) {
        System.out.println("generate otp");
        String otp=String.format("%06d",Integer.parseInt(String.valueOf(Math.random())));
        return otp;
    }
}
