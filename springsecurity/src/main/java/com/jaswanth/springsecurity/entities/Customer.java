package com.jaswanth.springsecurity.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="customer")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private int id;
    private String email;

    //cannot be accessed
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;
    @Column(name="role")
    private String role;


}
