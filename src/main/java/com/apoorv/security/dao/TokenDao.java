package com.apoorv.security.dao;

import com.apoorv.security.entities.Consumer;
import com.apoorv.security.entities.Token;
import com.apoorv.security.repository.ConsumerRepository;
import com.apoorv.security.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Apoorv Vardhman
 * @Github Apoorv-Vardhman
 * @LinkedIN apoorv-vardhman
 */
@Service
public class TokenDao {

    @Value("${app.jwt_refresh_token_expire_ms}")
    private Long refreshTokenDurationMs;
    private final TokenRepository tokenRepository;
    private final ConsumerRepository consumerRepository;


    public TokenDao(TokenRepository tokenRepository, ConsumerRepository consumerRepository) {
        this.tokenRepository = tokenRepository;
        this.consumerRepository = consumerRepository;
    }

    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public Token createRefreshToken(Long userId) {
        Token refreshToken = new Token();
        Consumer consumer = consumerRepository.findById(userId).orElseThrow(()->new RuntimeException("Resource not found with "+userId));
        refreshToken.setConsumer(consumer);
        refreshToken.setExpiry(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        return tokenRepository.save(refreshToken);
    }

}
