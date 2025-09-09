package com.bluetredint.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bluetredint.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/gateway")
@RequiredArgsConstructor
public class GatewayController {

    private final JwtUtil jwtUtil;

    @GetMapping("/validate")
    public String validateToken(@RequestParam String token) {
        if (jwtUtil.validateToken(token)) {
            return "Token is valid for user: " + jwtUtil.extractUsername(token);
        } else {
            return "Invalid or expired token";
            //12345
        }
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Shifor07 Investment App!";
    }
}

