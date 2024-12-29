package com.practice.login.controller;

import com.practice.login.entity.User;
import com.practice.login.model.LoginDto;
import com.practice.login.model.UserDto;
import com.practice.login.service.LoginService;
import com.practice.login.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService login;


    @GetMapping("/info")
    public ResponseEntity<String> getInfo(){
        return new ResponseEntity("This is info class", HttpStatus.OK);
    }

    @PostMapping("/register-user")
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserDto user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDto dto){
        String jwtToken = login.login(dto.getUsername(),dto.getPassword());
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/login-Otp")
    public ResponseEntity<String> loginOtp(@RequestParam("username") String username){
        String jwtToken = login.loginWithOtp(username);
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/verify-loginOtp")
    public ResponseEntity<String> loginWithOtp(@Valid @RequestBody LoginDto dto){
        String token = login.verifyOtp(dto.getUsername(),dto.getPassword());
        return ResponseEntity.ok(token);
    }
}
