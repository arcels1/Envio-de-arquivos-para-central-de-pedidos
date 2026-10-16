package com.gerenciamentofotos.vinicius.controller;

import com.gerenciamentofotos.vinicius.DTO.LoginRequestDTO;
import com.gerenciamentofotos.vinicius.DTO.LoginResponseDTO;
import com.gerenciamentofotos.vinicius.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        return new LoginResponseDTO(authService.login(request));
    }
}
