package com.practice.login.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDto {

    @NotEmpty(message = "please enter the valid username")
    private String username;

    @NotEmpty(message = "please enter the password or OTP")
    private String password;
}
