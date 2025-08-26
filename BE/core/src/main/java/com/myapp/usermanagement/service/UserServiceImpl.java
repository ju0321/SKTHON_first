package com.myapp.usermanagement.service;

import com.myapp.usermanagement.dto.LoginRequest;
import com.myapp.usermanagement.dto.SignupRequest;
import com.myapp.usermanagement.model.User;
import com.myapp.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User signup(SignupRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); // ⚠️ 실제로는 BCrypt 암호화 권장
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> login(LoginRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user != null && user.getClass().equals(request.getPassword())) {
            return user;
        }
        return null;
    }
}
