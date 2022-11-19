package com.apoorv.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Apoorv Vardhman
 * @Github Apoorv-Vardhman
 * @LinkedIN apoorv-vardhman
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerModel {
    private long id;
    private String name;
    private String email;
    private String password;
}
