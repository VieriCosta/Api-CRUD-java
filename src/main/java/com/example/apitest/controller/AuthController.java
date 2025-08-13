package com.example.apitest.controller;

import com.example.apitest.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    record LoginRequest(String username, String password) {}
    record TokenResponse(String token) {}

    private final JwtUtil jwt;

    public AuthController(JwtUtil jwt) {
        this.jwt = jwt;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        // DEMO: usuário fixo (admin/admin). Em produção, valide no banco e use senha com BCrypt.
        if ("admin".equals(req.username()) && "admin".equals(req.password())) {
            String token = jwt.generateToken(req.username());
            return ResponseEntity.ok(new TokenResponse(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
