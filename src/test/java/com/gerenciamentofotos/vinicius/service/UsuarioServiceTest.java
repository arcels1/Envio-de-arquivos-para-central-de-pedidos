package com.gerenciamentofotos.vinicius.service;

import com.gerenciamentofotos.vinicius.DTO.UsuarioRequestDTO;
import com.gerenciamentofotos.vinicius.DTO.UsuarioResponseDTO;
import com.gerenciamentofotos.vinicius.entity.Usuario;
import com.gerenciamentofotos.vinicius.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void deveCodificarSenhaAntesDeSalvar() {
        when(passwordEncoder.encode("senha-plana")).thenReturn("senha-codificada");
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UsuarioResponseDTO resposta = new UsuarioService(usuarioRepository, passwordEncoder)
                .criarUsuario(new UsuarioRequestDTO("Maria", "senha-plana", "USUARIO"));

        ArgumentCaptor<Usuario> captor = ArgumentCaptor.forClass(Usuario.class);
        verify(usuarioRepository).save(captor.capture());

        assertThat(captor.getValue().getSenha()).isEqualTo("senha-codificada");
        assertThat(captor.getValue().getAtivo()).isTrue();
        assertThat(resposta.nome()).isEqualTo("Maria");
    }
}
