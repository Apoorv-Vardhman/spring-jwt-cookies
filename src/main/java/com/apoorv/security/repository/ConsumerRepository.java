package com.apoorv.security.repository;

import com.apoorv.security.entities.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Apoorv Vardhman
 * @Github Apoorv-Vardhman
 * @LinkedIN apoorv-vardhman
 */
public interface ConsumerRepository extends JpaRepository<Consumer,Long> {
    Consumer findByEmail(String email);
}
