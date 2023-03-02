package com.example.camelmsa.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GetCurrentTimeBean {
    public String getCurrentTime() {
        return "time now is : " + LocalDateTime.now();
    }
}