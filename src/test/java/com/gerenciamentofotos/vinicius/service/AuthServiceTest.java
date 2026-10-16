package com.gerenciamentofotos.vinicius.service;

import com.gerenciamentofotos.vinicius.DTO.LoginRequestDTO;
import com.gerenciamentofotos.vinicius.entity.Role;
import com.gerenciamentofotos.vinicius.entity.Usuario;
import com.gerenciamentofotos.vinicius.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Test
    void deveGerarTokenComCredenciaisValidas() {
        Usuario usuario = new Usuario("admin", "hash", Role.ADMIN);
        when(usuarioRepository.findByNome("admin")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("senha", "hash")).thenReturn(true);
        when(jwtService.gerarToken(usuario)).thenReturn("token-gerado");

        String token = new AuthService(usuarioRepository, passwordEncoder, jwtService)
                .login(new LoginRequestDTO("admin", "senha"));

        assertThat(token).isEqualTo("token-gerado");
        verify(passwordEncoder).matches("senha", "hash");
        verify(jwtService).gerarToken(usuario);
    }

    @Test
    void deveRejeitarSenhaInvalida() {
        Usuario usuario = new Usuario("admin", "hash", Role.ADMIN);
        when(usuarioRepository.findByNome("admin")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("errada", "hash")).thenReturn(false);

        assertThatThrownBy(() -> new AuthService(usuarioRepository, passwordEncoder, jwtService)
                .login(new LoginRequestDTO("admin", "errada")))
                .isInstanceOf(ResponseStatusException.class)
                .extracting("statusCode").isEqualTo(org.springframework.http.HttpStatus.UNAUTHORIZED);

        verifyNoInteractions(jwtService);
    }

    @Test
    void deveRejeitarUsuarioInativo() {
        Usuario usuario = new Usuario("admin", "hash", Role.ADMIN);
        usuario.setAtivo(false);
        when(usuarioRepository.findByNome("admin")).thenReturn(Optional.of(usuario));

        assertThatThrownBy(() -> new AuthService(usuarioRepository, passwordEncoder, jwtService)
                .login(new LoginRequestDTO("admin", "senha")))
                .isInstanceOf(ResponseStatusException.class);

        verifyNoInteractions(passwordEncoder, jwtService);
    }
}
