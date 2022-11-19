package com.apoorv.security.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

/**
 * @author Apoorv Vardhman
 * @Github Apoorv-Vardhman
 * @LinkedIN apoorv-vardhman
 */
@Entity
@Table(name = "tokens")
@Getter
@Setter
@ToString
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Instant expiry;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id",referencedColumnName = "id")
    private Consumer consumer;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

}
