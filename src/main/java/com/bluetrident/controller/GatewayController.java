package com.bluetrident.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.bluetrident.security.JwtUtil;

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
        }
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Shifor07 Investment App!";
    }
}
