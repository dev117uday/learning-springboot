package com.example.sample.service;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    WeatherService weatherService;

    @Autowired
    ApplicationContext context;

    @Autowired
    private ObjectFactory<WeatherService> wFactory;

    public String getCurrentTemp() {
        return weatherService.getTodayTemp();
    }

    public String getCurrentTempDiff() {
        // not recommended
        // return context.getBean(WeatherService.class).getTodayTemp();
        return wFactory.getObject().getTodayTemp();
    }

}
