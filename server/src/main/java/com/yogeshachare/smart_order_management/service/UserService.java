package com.yogeshachare.smart_order_management.service;

import com.yogeshachare.smart_order_management.entity.User;
import com.yogeshachare.smart_order_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> findByEmail(String email){
        return userRepository.findAll();
    }
}
