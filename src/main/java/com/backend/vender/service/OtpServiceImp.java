package com.backend.vender.service;

import java.util.Random;

public class OtpServiceImp implements OtpService{

    @Override
    public String generateOtp(String phoneNumber) {
        System.out.println("generate otp");
        Random random = new Random();
        String otp = String.format("%06d", random.nextInt(1000000));
        return otp;
    }
}
