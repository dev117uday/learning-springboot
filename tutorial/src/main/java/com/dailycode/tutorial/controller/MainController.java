package com.dailycode.tutorial.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

	@Value("${welcome.message}")
	private String welcomeMessage ;

	@GetMapping
	public String HelloWorld() {
		return welcomeMessage;
	}

}
