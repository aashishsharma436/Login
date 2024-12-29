package com.practice.login.service.impl;

import com.practice.login.entity.User;
import com.practice.login.model.UserDto;
import com.practice.login.repository.UserRepository;
import com.practice.login.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    UserRepository repository;

    @Override
    public User saveUser(UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto,user);
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setAge(calculateAge(dto.getDob()));
        user.setMobileVerified(false);
        user.setEmailVerified(false);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setUsername(getUsername(dto.getFirstName(),dto.getLastName()));
        user.setWorkEmail(getEmail(dto.getFirstName(),dto.getLastName(),"google.com"));
        user.setMobile(dto.getCountryCode()+dto.getMobile());
        User user1 = repository.save(user);
        return user1;
    }

    private int calculateAge(LocalDate dob) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dob, currentDate);
        return period.getYears();
    }

    private String getUsername(String firstName, String lastName) {

        StringBuilder baseUsername = new StringBuilder(firstName.length() + lastName.length());

        baseUsername.append(firstName.length() >= 2 ? firstName.substring(0, 2).toLowerCase() : firstName.toLowerCase());
        baseUsername.append(lastName.length() >= 4 ? lastName.substring(0, 4).toLowerCase() : lastName.toLowerCase());

        // Check if the base username already exists
        String baseUsernameStr = baseUsername.toString();
        if (!repository.existsByUsername(baseUsernameStr)) {
            return baseUsernameStr;  // Return the base username if it's unique
        }

        // If base username exists,
        StringBuilder uniqueUsername = new StringBuilder(baseUsername);
        int counter = 1;

        // Continue checking until we find a unique username
        while (repository.existsByUsername(uniqueUsername.toString())) {
            uniqueUsername.setLength(baseUsername.length());  // Reset the StringBuilder to the base username
            uniqueUsername.append(counter++);  // Append the next counter value
        }

        return uniqueUsername.toString();  // Return the first available unique username
    }




    private String getEmail(String firstName, String lastName, String organisationDomain) {
        StringBuilder baseEmail = new StringBuilder(firstName.toLowerCase())
                .append(".")
                .append(lastName.toLowerCase())
                .append("@")
                .append(organisationDomain.toLowerCase());

        // Check if the base email already exists in the repository
        String baseEmailStr = baseEmail.toString();
        if (!repository.existsByWorkEmail(baseEmailStr)) {
            return baseEmailStr;
        }

        // check for numbered versions
        StringBuilder email = new StringBuilder(baseEmail);
        int counter = 1;

        // Generate the next available email by appending a counter
        while (repository.existsByWorkEmail(email.toString())) {
            email.setLength(baseEmail.length());  // Reset to the length of baseEmail
            email.append(counter++);  // Append the counter before the domain part
        }

        return email.toString();  // Return the first available unique email
    }




}
