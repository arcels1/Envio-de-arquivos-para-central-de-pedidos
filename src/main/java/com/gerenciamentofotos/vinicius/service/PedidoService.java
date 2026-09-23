package com.gerenciamentofotos.vinicius.service;

import com.gerenciamentofotos.vinicius.entity.ArquivoFotos;
import com.gerenciamentofotos.vinicius.entity.Pedido;
import com.gerenciamentofotos.vinicius.entity.Status;
import com.gerenciamentofotos.vinicius.entity.Usuario;
import com.gerenciamentofotos.vinicius.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final UsuarioAutenticadoService usuarioAutenticadoService;
    private final ArquivoFotosService arquivoFotosService;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioAutenticadoService usuarioAutenticadoService, ArquivoFotosService arquivoFotosService) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioAutenticadoService = usuarioAutenticadoService;
        this.arquivoFotosService = arquivoFotosService;
    }

    public Pedido createPedido(Pedido pedido, ArquivoFotos arquivoFotos){
        Usuario usuario = usuarioAutenticadoService.getUsuarioAutenticado();
        pedido.setUsuario(usuario);

        return pedidoRepository.save(pedido);

    }
    public Pedido alterarStatus(String id){
        Pedido pedido = pedidoRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setStatus(Status.PRONTO);
        return pedidoRepository.save(pedido);

    }
    public List<Pedido> pedidosHoje(){
        return pedidoRepository.findByTimestamp_Date(LocalDate.now());
    }

}
