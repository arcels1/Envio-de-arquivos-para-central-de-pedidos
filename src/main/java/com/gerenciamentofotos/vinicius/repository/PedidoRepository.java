package com.gerenciamentofotos.vinicius.repository;

import com.gerenciamentofotos.vinicius.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    List<Pedido> findByTimestamp_Date(LocalDate now);
}
