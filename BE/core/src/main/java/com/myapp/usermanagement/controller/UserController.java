package com.myapp.usermanagement.controller;

import com.myapp.usermanagement.dto.LoginRequest;
import com.myapp.usermanagement.dto.SignupRequest;
import com.myapp.usermanagement.model.User;
import com.myapp.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 회원가입 API
    @PostMapping("/signup")
    public User signup(@RequestBody SignupRequest request) {
        return userService.signup(request);
    }

    // 로그인 API
    @PostMapping("/login")
    public Optional<User> login(@RequestBody LoginRequest request) {
        Optional<User> user = userService.login(request);
        if (user == null) {
            throw new RuntimeException("아이디 또는 비밀번호가 잘못되었습니다.");
        }
        return user;
    }
}
