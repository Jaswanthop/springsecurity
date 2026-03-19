package com.jaswanth.springsecurity.exceptionhandling;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAccessDeniedException implements AccessDeniedHandler {



    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        LocalDateTime localDateTime = LocalDateTime.now();
        //checking if it is null or not
        String message=(accessDeniedException!=null?accessDeniedException.getMessage():"Unauthorized");
        String path=request.getRequestURI();
        response.setHeader("error-reasone","authentication failed");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        //own body
        response.setContentType("application/json;charset=UTF-8");
//values will be assigned at runtime
        String jsonResponse=String.format("{\"timestamp\": \"%s\", \"status\": %d, \"error\": \"%s\", \"message\": \"%s\", \"path\": \"%s\"}",localDateTime,HttpStatus.FORBIDDEN.value(),HttpStatus.FORBIDDEN.getReasonPhrase(),message,path);
        response.getWriter().write(jsonResponse);
    }

}
