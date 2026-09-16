package com.gerenciamentofotos.vinicius.repository;

import com.gerenciamentofotos.vinicius.entity.ArquivoFotos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArquivoFotosRepository extends JpaRepository<ArquivoFotos, UUID> {
}
