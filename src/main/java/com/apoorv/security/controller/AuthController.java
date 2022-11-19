package com.apoorv.security.controller;

import com.apoorv.security.coonfig.jwt.JwtUtils;
import com.apoorv.security.dao.ConsumerDao;
import com.apoorv.security.dao.TokenDao;
import com.apoorv.security.dto.request.LoginRequest;
import com.apoorv.security.dto.response.LoginResponse;
import com.apoorv.security.entities.Consumer;
import com.apoorv.security.entities.Token;
import com.apoorv.security.model.ConsumerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author Apoorv Vardhman
 * @Github Apoorv-Vardhman
 * @LinkedIN apoorv-vardhman
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class AuthController {

    @Autowired
    ConsumerDao consumerDao;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    TokenDao tokenDao;

    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping(path = "/Login")
    public  ResponseEntity<?> login(@RequestBody LoginRequest loginRequest)
    {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Consumer consumer = (Consumer) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(consumer);
        Token refreshToken = tokenDao.createRefreshToken(consumer.getId());
        ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(refreshToken.getToken());
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(consumer);
    }

    @PostMapping(path = "/Register")
    public @ResponseBody Object register(@RequestBody ConsumerModel consumerModel)
    {
        consumerModel.setPassword(passwordEncoder.encode(consumerModel.getPassword()));
        return consumerDao.create(consumerModel);
    }

    @GetMapping("/User")
    public @ResponseBody ResponseEntity<?> user(HttpServletRequest request)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HashMap<String,Object> response = new HashMap<>();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            response.put("user",consumerDao.findByEmail(username));
            return new ResponseEntity<>(response,HttpStatus.OK);
        } else {
            response.put("message","UnAuthenticated");
            return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
        }
    }


}
