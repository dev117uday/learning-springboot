package com.example.mongo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.mongo.Model.Student;
import com.example.mongo.entity.Address;
import com.example.mongo.entity.Gender;
import com.example.mongo.repository.StudentRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootApplication
public class MongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository studentRepository, MongoTemplate mongoTemplate) {
		return args -> {

			Address address = new Address("England", "London", "110092");

			Student student = new Student(
					"Uday", "Yadav", "yadav117uday@gmail.com", Gender.Male, address,
					List.of("english", "hindi", "maths"), BigDecimal.TEN, LocalDateTime.now());

			// var savedStudent = studentRepository.save(student);
			Student savedStudent;
			try {
				savedStudent = studentRepository.insert(student);
				System.out.println(savedStudent.toString());
			} catch (Exception e) {
				System.out.println("error ------------------------------");
			}

			Query query = new Query();
			query.addCriteria(Criteria.where("email").is(student.getEmail()));
			Student studentLst = mongoTemplate.findOne(query, Student.class);

			System.out.println(studentLst.toString());

			Optional<Student> studentOp = studentRepository.findStudentByEmail(student.getEmail());
			if (studentOp.isPresent()) {
				System.out.println("YES :: " + studentOp.get().toString());
			}

		};
	}

}
