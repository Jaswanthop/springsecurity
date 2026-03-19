package com.jaswanth.springsecurity.exceptionhandling;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomBasicAuthenticationPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
     LocalDateTime localDateTime = LocalDateTime.now();
     //checking if it is null or not
     String message=(authException!=null?authException.getMessage():"Unauthorized");
     String path=request.getRequestURI();
        response.setHeader("error-reasone","authentication failed");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        //own body
        response.setContentType("application/json;charset=UTF-8");
//values will be assigned at runtime
        String jsonResponse=String.format("{\"timestamp\": \"%s\", \"status\": %d, \"error\": \"%s\", \"message\": \"%s\", \"path\": \"%s\"}",localDateTime,HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.getReasonPhrase(),message,path);
         response.getWriter().write(jsonResponse);
    }
}
