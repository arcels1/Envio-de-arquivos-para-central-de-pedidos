package com.gerenciamentofotos.vinicius.service;

import com.gerenciamentofotos.vinicius.entity.Usuario;
import com.gerenciamentofotos.vinicius.repository.UsuarioRepository;
import com.gerenciamentofotos.vinicius.security.PasswordConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario criarUsuario(Usuario usuario){
        usuario.setSenha(
                passwordEncoder.encode(usuario.getSenha()
                ));
        return usuarioRepository.save(usuario);
    }

    public Usuario getUsuarioPorID(UUID id){
           return usuarioRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("Usuario Não Encontrado"));
    }

    public List<Usuario> listarUsuario(){
        return usuarioRepository.findAll();
    }

    public Usuario setUsuarioAtivacao(UUID id){
        Usuario usuario = getUsuarioPorID(id);
        boolean ativo = usuario.getAtivo();
        usuario.setAtivo(!ativo);
        usuarioRepository.save(usuario);
        return usuario;
    }
}
