package com.myapp.usermanagement;

import com.myapp.usermanagement.model.User;
import com.myapp.usermanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    void 회원가입_저장_확인() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword(passwordEncoder.encode("1234"));
        user.setEmail("test@example.com");

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("testUser");
        assertThat(passwordEncoder.matches("1234", savedUser.getPassword())).isTrue();
    }

    @Test
    void 사용자_조회_확인() {
        User user = new User();
        user.setUsername("findUser");
        user.setPassword(passwordEncoder.encode("pass"));
        user.setEmail("find@example.com");
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByUsername("findUser");

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("find@example.com");
    }
}
