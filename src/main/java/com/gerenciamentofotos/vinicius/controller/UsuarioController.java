package com.gerenciamentofotos.vinicius.Controller;

import com.gerenciamentofotos.vinicius.DTO.UsuarioRequestDTO;
import com.gerenciamentofotos.vinicius.DTO.UsuarioResponseDTO;
import com.gerenciamentofotos.vinicius.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/create")
    public UsuarioResponseDTO createUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO){
        return usuarioService.criarUsuario(usuarioRequestDTO);
    }
    @GetMapping("/lista")
    public List<UsuarioResponseDTO> retornarUsuarios(){
        return usuarioService.listarUsuario();
    }
    @PutMapping("/alterarAtivacao")
    public UsuarioResponseDTO alterarAtivacao(@RequestBody String id){
        return usuarioService.alterarAtivoUsuario(id);
    }

}
