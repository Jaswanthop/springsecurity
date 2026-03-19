package com.jaswanth.springsecurity.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @GetMapping(path="/myCards")
    public String getCardDetails()
    {
        return "Card Details";
    }
}
