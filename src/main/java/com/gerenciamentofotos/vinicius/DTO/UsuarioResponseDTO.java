package com.gerenciamentofotos.vinicius.DTO;

import com.gerenciamentofotos.vinicius.entity.Usuario;

public record UsuarioResponseDTO(String id, String nome, String role, Boolean ativo) {
    public static UsuarioResponseDTO from(Usuario usuario){
        return new UsuarioResponseDTO(
                String.valueOf(usuario.getId()),
                usuario.getNome(),
                String.valueOf(usuario.getRole()),
                usuario.getAtivo());
    }
}
