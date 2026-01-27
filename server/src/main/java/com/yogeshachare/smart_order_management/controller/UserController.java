package com.yogeshachare.smart_order_management.controller;

import com.yogeshachare.smart_order_management.dto.UserRegistrationDto;
import com.yogeshachare.smart_order_management.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserRegistrationDto userDto) {
        userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully");
    }
}
