package com.jaswanth.springsecurity.repositry;

import com.jaswanth.springsecurity.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepositry extends CrudRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email);
}
