package com.spring.learn.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			Student uday = new Student(1L, "uday", "email@email.com", LocalDate.of(2000, Month.JANUARY, 10));
			Student anotheruday = new Student(2L, "another uday", "uday_email@email.com",
					LocalDate.of(2002, Month.FEBRUARY, 20));

			repository.saveAll(List.of(uday, anotheruday));
		};
	}

}
