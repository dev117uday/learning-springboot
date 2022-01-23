package com.example.mongo.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.mongo.entity.Address;
import com.example.mongo.entity.Gender;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "learn")
public class Student {

	@Id
	private String Id;
	private String firstName;
	private String lastName;
	private Gender gender;
	@Indexed(unique = true)
	private String email;
	private Address address;
	private List<String> favSubjects;
	private BigDecimal totalSpentInBooks;
	private LocalDateTime createdAt;

	public Student(String firstName, String lastName, String email, Gender gender, Address address,
			List<String> favSubjects, BigDecimal totalSpentInBooks, LocalDateTime createdAt) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.address = address;
		this.favSubjects = favSubjects;
		this.totalSpentInBooks = totalSpentInBooks;
		this.createdAt = createdAt;
	}

}
