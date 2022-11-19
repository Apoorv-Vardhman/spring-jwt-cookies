package com.apoorv.security.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Apoorv Vardhman
 * @Github Apoorv-Vardhman
 * @LinkedIN apoorv-vardhman
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}
