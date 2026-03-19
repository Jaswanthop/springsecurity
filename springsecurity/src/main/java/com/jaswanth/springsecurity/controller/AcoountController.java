package com.jaswanth.springsecurity.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AcoountController {

    @GetMapping(path="/myAccount")
    public String getAccountDetails(Authentication authentication) {
        return authentication.getName();
    }
}
