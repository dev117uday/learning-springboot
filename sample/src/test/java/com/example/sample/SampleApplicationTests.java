package com.example.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.example.sample.service.UserService;
import com.example.sample.service.WeatherService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class SampleApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	void getTime() {
		UserService userService1 = context.getBean(UserService.class);
		UserService userService2 = context.getBean(UserService.class);
		log.info("1 : " + userService1.getCurrentTemp());
		log.info("2 : " + userService2.getCurrentTemp());
		// both time is same even though service is different cuz @service is singleton

		log.info(userService1.getCurrentTempDiff());
		log.info(userService2.getCurrentTempDiff());
	}

	@Test
	void singletonBeanScope() {
		UserService userService1 = context.getBean(UserService.class);
		UserService userService2 = context.getBean(UserService.class);
		assertEquals(userService1.hashCode(), userService2.hashCode());
	}

	@Test
	void prototypeBeanScope() {
		WeatherService weatherService1 = context.getBean(WeatherService.class);
		WeatherService weatherService2 = context.getBean(WeatherService.class);
		assertNotEquals(weatherService1.hashCode(), weatherService2.hashCode());
	}

}
