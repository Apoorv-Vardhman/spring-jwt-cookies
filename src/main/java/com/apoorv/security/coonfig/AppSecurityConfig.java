package com.apoorv.security.coonfig;

import com.apoorv.security.coonfig.jwt.TokenFilter;
import com.apoorv.security.services.ConsumerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Apoorv Vardhman
 * @Github Apoorv-Vardhman
 * @LinkedIN apoorv-vardhman
 */
@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    @Autowired
    private ConsumerDetailsService consumerDetailsService;

    @Bean
    public TokenFilter authenticationJwtTokenFilter() {
        return new TokenFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                /*1. csrf*/
                .csrf().disable()
                /*2. requests*/
                .authorizeRequests()
                .antMatchers("/api/Register","/api/Login").permitAll()
                .anyRequest().authenticated()
                .and()
                /*3. user detail service*/
                .userDetailsService(consumerDetailsService)
                /*session management*/
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService)
    {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(authProvider);
    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
