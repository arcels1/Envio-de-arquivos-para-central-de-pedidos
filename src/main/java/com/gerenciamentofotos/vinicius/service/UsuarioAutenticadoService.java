package com.gerenciamentofotos.vinicius.service;

import com.gerenciamentofotos.vinicius.entity.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class UsuarioAutenticadoService {
    private final UsuarioService usuarioService;

    public UsuarioAutenticadoService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Usuario getUsuarioAutenticado(){
        Authentication authentication =
                 SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        String usuarioId = authentication.getName();
        return usuarioService.getUsuarioPorID(UUID.fromString(usuarioId));

    }
}
