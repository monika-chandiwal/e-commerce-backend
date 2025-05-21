package com.backend.vender.controller;

import com.backend.vender.repository.VendorRepo;
import com.backend.vender.service.OtpService;
import com.backend.vender.service.SmsService;
import com.backend.vender.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    String phoneNumber="";
    @PostMapping("/otpRequest")
    public ResponseEntity<Map<String, String>> otpVerify(@RequestBody Map<String, String> payload) {
        phoneNumber = payload.get("phoneNumber");
        String otp = otpService.generateOtp(phoneNumber);
        otpService.sendOtp(phoneNumber, otp);

        Map<String, String> response = new HashMap<>();
        response.put("message", "OTP sent to your phone. Please check your phone.");
        return ResponseEntity.ok(response);
    }


    @PostMapping("/verifyOtp")
    public ResponseEntity<Map<String, String>> verifyOtp(@RequestBody Map<String, String> request) {
        String otp = request.get("otp");
        String phoneNumber = request.get("phoneNumber");

        boolean isVerified = otpService.verifyOtp(phoneNumber, otp);

        Map<String, String> response = new HashMap<>();
        if (isVerified) {
            response.put("message", "OTP verified.");
            return ResponseEntity.ok(response);
        }
        response.put("message", "OTP not verified");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }


}
