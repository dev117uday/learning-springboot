package com.example.jpa.repository;

import java.util.List;

import com.example.jpa.entity.Guardian;
import com.example.jpa.entity.Student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void saveStudent() {
		Student student1 = Student.builder().emailId("devuday@gmail.com").firstName("uday").lastName("yadav")
				// .gaurdianName("god")
				// .guardingEmail("god@god.com")
				// .guardianMobile("123456789")
				.build();

		studentRepository.save(student1);
	}

	@Test
	public void printAllStudent() {
		List<Student> students = studentRepository.findAll();
		System.out.println(students.toString());
	}

	@Test
	public void saveStudentWithGuardian() {

		Guardian guardian = new Guardian("dad", "dad@gmail.com", "999999");

		Student student1 = Student.builder().emailId("udayyadav@gmail.com").firstName("master").lastName("yadav")
				.guardian(guardian)
				.build();

		studentRepository.save(student1);
	}

	@Test
	public void searchByFirstName() {
		List<Student> students = studentRepository.findByFirstName("uday");
		System.out.println(students.toString());
	}

	@Test
	public void searchByEmailNative() {
		Student students = studentRepository.getStudentByEmailAddressNative("udayyadav@gmail.com");
		System.out.println(students.toString());
	}

	@Test
	public void findStudentByEmail() {
		Student students = studentRepository.findStudentByEmail("udayyadav@gmail.com");
		System.out.println(students.toString());
	}

	@Test
	public void searchByEmailNativeNamed() {
		Student students = studentRepository.getStudentByEmailAddressNativeNamed("udayyadav@gmail.com");
		System.out.println(students.toString());
	}

	@Test
	public void updatStudentEmailIdByFirstNameTest() {
		Integer student = studentRepository.updatStudentEmailIdByFirstName("master", "dev09uday@gmail.com");
		System.out.println(student);
	}

}
