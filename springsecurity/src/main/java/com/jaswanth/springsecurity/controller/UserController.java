package com.jaswanth.springsecurity.controller;


import com.jaswanth.springsecurity.entities.Customer;
import com.jaswanth.springsecurity.repositry.CustomerRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final  CustomerRepositry customerRepositry;
    private final PasswordEncoder passwordEncoder;

    public UserController(CustomerRepositry customerRepositry, PasswordEncoder passwordEncoder) {
        this.customerRepositry = customerRepositry;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        try{
           String hashesPwd = passwordEncoder.encode(customer.getPwd());
           customer.setPwd(hashesPwd);
           Customer savedCustomer=customerRepositry.save(customer);
           return ResponseEntity.ok("success");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
