package com.practice.login.service;

public interface RedisService {
    String generateOtp(String email);
    String verifyOtp(String email, String otp,String type);
    String generateRandomOtp();
    boolean verifyLoginOtp(String otp,String username);
}
