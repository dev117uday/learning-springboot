package com.example.camelmsa.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeciderBean {
    public boolean isThisConditionMet(String body) {
        log.info("{}", body);
        return true;
    }
}