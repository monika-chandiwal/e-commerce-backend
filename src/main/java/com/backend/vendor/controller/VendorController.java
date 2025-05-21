package com.backend.vendor.controller;

import com.backend.customer.model.Users;
import com.backend.vendor.model.Vendor;
import com.backend.vendor.repository.VendorRepo;
import com.backend.vendor.service.OtpService;
import com.backend.vendor.service.VendorService;
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


    @PostMapping("/otpRequest")
    public ResponseEntity<Map<String, String>> otpVerify(@RequestBody Map<String, String> payload) {
        String phoneNumber = payload.get("phoneNumber");
        //String otp = otpService.generateOtp(phoneNumber);
        otpService.sendOtp(phoneNumber);

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


    @PostMapping("/signup")
    public ResponseEntity<?> createVendor(@RequestBody Vendor vendor){
        try {
           // System.out.println(vendor.getName() +" "+vendor.getEmail());
            if(vendor.getPhoneNumber()==null){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Your phone number are not added or verified ! ");
            }
            if (vendorRepo.findByPhoneNumber(vendor.getPhoneNumber())!=null) {
                return ResponseEntity.status(HttpStatus.CONFLICT) // 409
                        .body("Vendor already exists with phone "+ vendor.getPhoneNumber());
            } else {


                Vendor newVendor = vendorService.saveVendor(vendor);
                System.out.println(newVendor);
                return ResponseEntity.status(HttpStatus.CREATED).body(newVendor);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred");
        }
    }
    @PostMapping("/login")
        public ResponseEntity <?> validateVendor(@RequestBody Map<String, String> request){
            try {
                //System.out.println(request);
                Vendor vendor=vendorRepo.findByEmailAndPassword(request.get("email"),request.get("password"));
                if (vendor==null) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body("Invalid Email or Password ");
                } else {
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(vendor);
                }
                } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("An unexpected error occurred");
            }
        }




}
