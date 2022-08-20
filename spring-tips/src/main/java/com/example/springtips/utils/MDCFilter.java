package com.example.springtips.utils;

import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class MDCFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String userId = UUID.randomUUID().toString();
            MDC.put("UserId",userId);
            filterChain.doFilter(request, response);
        } finally {
//            MDC.remove("UserId");
            MDC.clear();
        }

    }
}
