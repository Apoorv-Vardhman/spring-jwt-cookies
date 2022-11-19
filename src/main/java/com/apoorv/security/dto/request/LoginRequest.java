package com.apoorv.security.dto.request;

import lombok.*;

/**
 * @author Apoorv Vardhman
 * @Github Apoorv-Vardhman
 * @LinkedIN apoorv-vardhman
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String username;
    private String password;

}
