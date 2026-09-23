package com.gerenciamentofotos.vinicius.DTO;

import com.gerenciamentofotos.vinicius.entity.Role;
import com.gerenciamentofotos.vinicius.entity.Usuario;

public record UsuarioRequestDTO(String nome, String senha, String role) {
    public Usuario toEntity(){
        return new Usuario( nome, senha,Role.valueOf(role));
    }
}
