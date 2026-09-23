package com.gerenciamentofotos.vinicius.service;

import com.gerenciamentofotos.vinicius.DTO.UsuarioRequestDTO;
import com.gerenciamentofotos.vinicius.DTO.UsuarioResponseDTO;
import com.gerenciamentofotos.vinicius.entity.Role;
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
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public UsuarioResponseDTO usuarioParaDTO(Usuario usuario){
        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO(
                String.valueOf(usuario.getId()),
                usuario.getNome(),
                String.valueOf(usuario.getRole()),
                usuario.getAtivo());
        return  usuarioDTO;
    }


    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioDTO){
        return usuarioParaDTO(usuarioRepository.save(usuarioDTO.toEntity()));
    }

    public Usuario getUsuarioPorID(UUID id){
           return usuarioRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("Usuario Não Encontrado"));
    }

    public List<UsuarioResponseDTO> listarUsuario(){
        return usuarioRepository.findAll().stream().map(usuario -> UsuarioResponseDTO.from(usuario)).collect(Collectors.toUnmodifiableList());
    }

    public UsuarioResponseDTO alterarAtivoUsuario(String id){
        Usuario usuario = getUsuarioPorID(UUID.fromString(id));
        boolean ativo = usuario.getAtivo();
        usuario.setAtivo(!ativo);
        usuario = usuarioRepository.save(usuario);
        return UsuarioResponseDTO.from(usuario);
    }
}
