package com.apoorv.security.repository;

import com.apoorv.security.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Apoorv Vardhman
 * @Github Apoorv-Vardhman
 * @LinkedIN apoorv-vardhman
 */
@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> findByToken(String token);
}
