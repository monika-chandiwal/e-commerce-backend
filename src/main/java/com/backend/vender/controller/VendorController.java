package com.backend.vender.controller;

import com.backend.customer.model.Users;
import com.backend.vender.model.Vender;
import com.backend.vender.repository.VendorRepo;
import com.backend.vender.service.OtpService;
import com.backend.vender.service.SmsService;
import com.backend.vender.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
public class VendorController {

    @Autowired
    private VendorRepo vendorRepo;
    @Autowired
    private VendorService vendorService;

    @Autowired
    private SmsService smsService;
    @Autowired
    private OtpService otpService;

    @PostMapping("/vendor/otpVerification")
    public String otpVerify(@RequestBody String phoneNumber){
        String otp=otpService.generateOtp(phoneNumber);
        smsService.sendOtp(phoneNumber,otp);
        return "OTP sent to your phone. please check your phone.";
    }

}
