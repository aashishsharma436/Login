package com.practice.login.service;

import com.practice.login.entity.User;
import com.practice.login.model.UserDto;

public interface UserService {
    User saveUser(UserDto user);
}
