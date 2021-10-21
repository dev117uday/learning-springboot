package com.student.student.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner (StudentRepository studentRepository) {
        return args -> {
            Student s = new Student( "uday", LocalDate.of(2000,02,01),"uday@gmail.com");
            Student s1 = new Student( "uday", LocalDate.of(2000,02,01),"uday1@gmail.com");
            Student s2 = new Student( "uday", LocalDate.of(2000,02,01),"uday2@gmail.com");
            Student s3 = new Student( "uday", LocalDate.of(2000,02,01),"uday3@gmail.com");
            studentRepository.saveAll(List.of(s,s1,s2,s3));
        };
    }

}
