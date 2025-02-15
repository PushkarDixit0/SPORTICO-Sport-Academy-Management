package com.sportico.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sportico.Service.OtpService;

@RestController
@RequestMapping("/otp")
public class OtpController {
	@Autowired
    private final OtpService otpService;

    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/generate")
    public String generateOtp(@RequestParam String email) {

        return "OTP for " + email + " is: " + otpService.generateOTP(email);
    }

    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp) {
        if (otpService.verifyOTP(email, otp)) {
            otpService.invalidateOTP(email);
            return "OTP Verified Successfully!";
        } else {
            return "Invalid OTP or OTP Expired!";
        }
    }
}
