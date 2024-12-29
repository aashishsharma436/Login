package com.practice.login.service;

public interface OtpService {
    void sendOtpEmail(String to, String otp);
    void sendOtpSms(String Otp,String mobile);
}
