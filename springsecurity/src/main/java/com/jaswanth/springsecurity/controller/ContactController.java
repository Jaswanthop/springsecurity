package com.jaswanth.springsecurity.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping(path="/contact")
    public String  saveContactInquiryDetails(){
        return "Inquiry details are saved";
    }
}

