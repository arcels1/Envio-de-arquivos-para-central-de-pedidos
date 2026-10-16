package com.gerenciamentofotos.vinicius.service;

import com.gerenciamentofotos.vinicius.DTO.LoginRequestDTO;
import com.gerenciamentofotos.vinicius.entity.Usuario;
import com.gerenciamentofotos.vinicius.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String login(LoginRequestDTO request) {
        Usuario usuario = usuarioRepository.findByNome(request.nome())
                .orElseThrow(() -> credenciaisInvalidas());

        if (!Boolean.TRUE.equals(usuario.getAtivo())
                || !passwordEncoder.matches(request.senha(), usuario.getSenha())) {
            throw credenciaisInvalidas();
        }

        return jwtService.gerarToken(usuario);
    }

    private ResponseStatusException credenciaisInvalidas() {
        return new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "Nome ou senha inválidos");
    }
}
