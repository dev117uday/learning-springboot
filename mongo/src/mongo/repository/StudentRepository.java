package com.example.mongo.repository;

import com.example.mongo.Model.Student;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student,String> {

    Optional<Student> findStudentByEmail(String email);
	
}
