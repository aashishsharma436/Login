package com.practice.login.service.impl;

import com.practice.login.entity.User;
import com.practice.login.model.LoginDto;
import com.practice.login.repository.UserRepository;
import com.practice.login.service.LoginService;
import com.practice.login.service.OtpService;
import com.practice.login.service.RedisService;
import com.practice.login.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Optional;

@Service
public class LoginImpl implements LoginService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisService redis;

    @Autowired
    private UserRepository repository;

    @Autowired
    private OtpService otpService;

    @Override
    public String loginWithOtp(String username) {
        Optional<User> user = Optional.of(repository.getPassword(username));
        if (user.isPresent()){
            String email = user.get().getWorkEmail();
            String otp = redis.generateOtp(username);
            otpService.sendOtpEmail(email,otp);
        }

        return "OTP sent successfully.";
    }

    @Override
    public String verifyOtp(String username, String otp) {
        boolean verify = redis.verifyLoginOtp(otp, username);
        if (verify)  return jwtUtil.generateToken(username);
        throw new IllegalArgumentException("Invalid OTP");
    }
    @Override
    public String login(String username, String password) {
        Optional<User> user = Optional.ofNullable(repository.getPassword(username));
        if (user.isPresent()){
            boolean flag = encoder.matches(password,user.get().getPassword());
            if (!flag) {
                throw new InputMismatchException("Enter valid password");
            }
            return jwtUtil.generateToken(username);
        }
        else throw new InputMismatchException("Enter valid username");
    }
}
