package com.practice.login.service.impl;

import com.practice.login.service.OtpService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${twilio.account_sid}")
    private String SSID;

    @Value("${twilio.auth_token}")
    private String token;

    @Value("${twilio.number}")
    private String number;

    @Override
    public void sendOtpEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp);
        mailSender.send(message);
    }

    @Override
    public void sendOtpSms(String otp,String mobile) {
        Twilio.init(SSID,token);
        Message.creator(new PhoneNumber(mobile),new PhoneNumber(number),otp).create();
    }
}
