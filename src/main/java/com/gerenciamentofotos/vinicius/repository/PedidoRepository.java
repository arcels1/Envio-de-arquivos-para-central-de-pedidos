package com.gerenciamentofotos.vinicius.repository;

import com.gerenciamentofotos.vinicius.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}
