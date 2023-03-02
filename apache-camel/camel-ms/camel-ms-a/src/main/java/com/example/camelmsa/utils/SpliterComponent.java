package com.example.camelmsa.utils;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SpliterComponent {
    public List<String> spilt(String body) {
        return Arrays.stream(body.split(" ")).toList();
    }
}
