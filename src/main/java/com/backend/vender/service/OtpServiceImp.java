package com.backend.vender.service;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpServiceImp implements OtpService {

    @Value("${twilio.account.sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth.token}")
    private String AUTH_TOKEN;

    @Value("${twilio.service.sid}")
    private String SERVICE_SID;

    @Override
    public String generateOtp(String phoneNumber) {

        Random random = new Random();
        String Otp=String.format("%06d", random.nextInt(1000000));
        System.out.println("generate otp : "+Otp);
        return Otp;
    }

    @Override
    public void sendOtp(String phoneNumber,String otp) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Verification verification = Verification.creator(
                        SERVICE_SID,
                        "+91" + phoneNumber,
                        "sms")
                .create();

        System.out.println("OTP sent, SID: " + verification.getSid());
    }

    @Override
    public boolean verifyOtp(String phoneNumber, String code) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        try {
            VerificationCheck verificationCheck = VerificationCheck.creator(SERVICE_SID)
                    .setTo("+91" + phoneNumber)
                    .setCode(code)
                    .create();

            return "approved".equals(verificationCheck.getStatus());
        } catch (Exception e) {
            System.err.println("OTP Verification failed: " + e.getMessage());
            return false;
        }
    }
}
