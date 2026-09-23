package com.gerenciamentofotos.vinicius.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class ArquivoFotos {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String tamanho;
    private String papel;
    private String pathArquivos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedidoId")
    private Pedido pedido;



}
