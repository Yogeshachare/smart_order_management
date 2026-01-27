package com.yogeshachare.smart_order_management.service;

import com.yogeshachare.smart_order_management.dto.UserRegistrationDto;
import com.yogeshachare.smart_order_management.entity.User;
import com.yogeshachare.smart_order_management.exception.UserAlreadyExistsException;
import com.yogeshachare.smart_order_management.exception.UserDoesNotExisitException;
import com.yogeshachare.smart_order_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(UserRegistrationDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistsException(
                    "User already exists with email: " + dto.getEmail());
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
