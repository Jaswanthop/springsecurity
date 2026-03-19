package com.jaswanth.springsecurity.config;

import com.jaswanth.springsecurity.entities.Customer;
import com.jaswanth.springsecurity.repositry.CustomerRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankUserDetailsService implements UserDetailsService {
    private  final CustomerRepositry customerRepositry;

    /**
     * @param username the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Customer customer= customerRepositry.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
      //authorities of user
      List<GrantedAuthority> grantedAuthorities = Arrays.asList(new SimpleGrantedAuthority(customer.getRole()));

        return new User(customer.getEmail(), customer.getPwd(),  grantedAuthorities);
    }
}
