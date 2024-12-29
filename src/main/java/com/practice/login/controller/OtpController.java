package com.practice.login.controller;

import com.practice.login.model.SmsDto;
import com.practice.login.service.OtpService;
import com.practice.login.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
public class OtpController {
    @Autowired
    private RedisService redisService;

    @Autowired
    private OtpService otpService;

    @GetMapping("/info")
    public String info() {
        return "This is Otp Controller";
    }

    @PostMapping("/send")
    public String sendOtp(@RequestBody SmsDto dto) {
        String otp = redisService.generateOtp(dto.getId());
        if (dto.getType().equals("email")) otpService.sendOtpEmail(dto.getId(), dto.getOtp());
        else otpService.sendOtpSms(dto.getOtp(),dto.getId());
        return "OTP sent to " + dto.getId();
    }

    @PostMapping("/verify")
    public String verifyOtp(@RequestBody SmsDto dto) {
        String res = redisService.verifyOtp(dto.getId(),dto.getOtp(), dto.getType());
        return res;
    }
}
