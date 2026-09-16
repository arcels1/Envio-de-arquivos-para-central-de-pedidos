package com.gerenciamentofotos.vinicius.service;

import com.gerenciamentofotos.vinicius.entity.Pedido;
import com.gerenciamentofotos.vinicius.entity.Usuario;
import com.gerenciamentofotos.vinicius.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final UsuarioAutenticadoService usuarioAutenticadoService;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioAutenticadoService usuarioAutenticadoService) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioAutenticadoService = usuarioAutenticadoService;
    }

    public Pedido createPedido(Pedido pedido){
        Usuario usuario = usuarioAutenticadoService.getUsuarioAutenticado();
        pedido.setUsuario(usuario);

    }

}
