package com.example.sample.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class WeatherService {

    String time = LocalDateTime.now().toString();
    int temp = new Random().nextInt(60);

    public String getTodayTemp() {
        return time + " -> " + temp;
    }
}
