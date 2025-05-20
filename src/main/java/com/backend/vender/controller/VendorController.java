package com.backend.vender.controller;

import com.backend.vender.repository.VendorRepo;
import com.backend.vender.service.OtpService;
import com.backend.vender.service.SmsService;
import com.backend.vender.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    private VendorRepo vendorRepo;
    @Autowired
    private VendorService vendorService;


    @Autowired
    private OtpService otpService;

    @PostMapping("/otpVerification")
    public String otpVerify(@RequestBody String phoneNumber){
        String otp=otpService.generateOtp(phoneNumber);
        System.out.println("inside otpVerify");
        otpService.sendOtp(phoneNumber);
        return "OTP sent to your phone. please check your phone.";
    }

}
