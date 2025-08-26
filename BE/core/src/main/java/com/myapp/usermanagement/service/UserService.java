package com.myapp.usermanagement.service;

import com.myapp.usermanagement.dto.LoginRequest;
import com.myapp.usermanagement.dto.SignupRequest;
import com.myapp.usermanagement.model.User;

import java.util.Optional;

public interface UserService {
    User signup(SignupRequest request);
    Optional<User> login(LoginRequest request);
}
