package com.gerenciamentofotos.vinicius.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gerenciamentofotos.vinicius.entity.Usuario;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

@Service
public class JwtService {
    private final SecretKey secretKey;

    public JwtService(@Value("${jwt.secret}") String secret) {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),"HmacSHA512");
    }
    public String gerarToken(Usuario usuario){
        Instant agora = Instant.now();
        return JWT.create()
                .withSubject(usuario.getId().toString())
                .withClaim("nome",usuario.getNome())
                .withClaim("role",usuario.getRole().name())
                .withIssuedAt(agora)
                .withExpiresAt(agora.plusSeconds(3600))
                .sign(Algorithm.HMAC512(secretKey.getEncoded()));

    }
}
