package com.apoorv.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Apoorv Vardhman
 * @Github Apoorv-Vardhman
 * @LinkedIN apoorv-vardhman
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public String index()
    {
        return "Hello world";
    }
}
