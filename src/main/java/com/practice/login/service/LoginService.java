package com.practice.login.service;

import com.practice.login.model.LoginDto;

public interface LoginService {
    String login(String username, String password);
    String verifyOtp(String username, String otp);
    String loginWithOtp(String username);
}
