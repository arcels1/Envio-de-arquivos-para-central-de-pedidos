package com.gerenciamentofotos.vinicius.service;

import com.gerenciamentofotos.vinicius.entity.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class UsuarioAutenticadoService {
    private final UsuarioService usuarioService;

    public UsuarioAutenticadoService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Usuario getUsuarioAutenticado(){
        Authentication authentication =
                 SecurityContextHolder.getContext().getAuthentication();
        String usuarioId = authentication.getName();
        return usuarioService.getUsuarioPorID(UUID.fromString(usuarioId));

    }
}
