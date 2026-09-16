package com.gerenciamentofotos.vinicius.repository;

import com.gerenciamentofotos.vinicius.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository
extends JpaRepository<Usuario, UUID> {
}
