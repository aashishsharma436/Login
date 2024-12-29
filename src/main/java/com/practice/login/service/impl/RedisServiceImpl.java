package com.practice.login.service.impl;

import com.practice.login.repository.UserRepository;
import com.practice.login.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserRepository repository;

    private static final int OTP_LENGTH = 6;
    @Override
    public String generateOtp(String email) {
        String otp = generateRandomOtp();
        redisTemplate.opsForValue().set(email, otp, 10, TimeUnit.MINUTES); // OTP expires in 10 minutes
        return otp;
    }

    @Override
    public String verifyOtp(String email, String otp,String type) {
        String storedOtp = redisTemplate.opsForValue().get(email);
        boolean verify = storedOtp != null && storedOtp.equals(otp);
        int update;
        if (type.equals("email")) update = repository.verifyEmail(LocalDateTime.now(),email);
        else update = repository.verifyMobile(LocalDateTime.now(),email);
        if (!verify) return "Incorrect OTP";
        else if (update!=1) return "Unable to verify the otp";
        else return "OTP Verified successfully";
    }

    @Override
    public String generateRandomOtp() {
        Random rand = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(rand.nextInt(10));
        }
        return otp.toString();
    }

    @Override
    public boolean verifyLoginOtp(String otp,String username){
        String storedOtp = redisTemplate.opsForValue().get(username);
        return storedOtp != null && storedOtp.equals(otp);
    }
}
