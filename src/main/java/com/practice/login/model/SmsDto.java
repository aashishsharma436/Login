package com.practice.login.model;

import lombok.Data;

@Data
public class SmsDto {
    private String id;
    private String otp;
    private String type;
}
