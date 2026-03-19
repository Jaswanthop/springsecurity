package com.jaswanth.springsecurity.config;


import com.jaswanth.springsecurity.exceptionhandling.CustomAccessDeniedException;
import com.jaswanth.springsecurity.exceptionhandling.CustomBasicAuthenticationPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {
    @Bean

    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
      //  http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
       // http.redirectToHttps((https) -> https.requestMatchers(AnyRequestMatcher.INSTANCE));
        http.sessionManagement(smc->smc.maximumSessions(1).expiredUrl("/login?expired=true").maxSessionsPreventsLogin(true));
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("myLoans","myAccount","myBalance","/myCards").authenticated().requestMatchers("/contact","/notices","/error","/register").permitAll());
        http.formLogin(withDefaults());
        //changing exception handling
        http.httpBasic(hbc->hbc.authenticationEntryPoint(new CustomBasicAuthenticationPoint()));
        http.exceptionHandling(ehc->ehc.accessDeniedHandler(new CustomAccessDeniedException()));
        return http.build();
    }
//user details manager
   // @Bean
    //public UserDetailsService userDetailsService(DataSource dataSource) {
//        UserDetails user= User.withUsername("user").password("{noop}user@21#21").authorities("read").build();
//        UserDetails admin= User.withUsername("admin").password("{bcrypt}$2a$12$XglVqY4cj9WtMW1JzAmxW.8BV1vEafjw9eZgNU8QfpeJ.7uSTHXee").authorities("admin").build();
//        return new InMemoryUserDetailsManager(user,admin);
       // return new JdbcUserDetailsManager(dataSource);

   // }
    //Password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

}
