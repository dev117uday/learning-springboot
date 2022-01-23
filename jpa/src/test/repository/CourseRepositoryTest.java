package com.example.jpa.repository;

import java.util.List;

import com.example.jpa.entity.Course;
import com.example.jpa.entity.Guardian;
import com.example.jpa.entity.Student;
import com.example.jpa.entity.Teacher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseRepositoryTest {

	@Autowired
	private CourseRepository courseRepository;

	@Test
	public void saveCourse() {
		List<Course> courses = courseRepository.findAll();
		System.out.println("courses = " + courses);
	}

	@Test
	public void saveCourseWithTeacher() {
		Teacher teacher = Teacher.builder().firstName("dumb").lastName("dumb").build();
		Course course = Course.builder().title("Python").credit(6).teacher(teacher).build();
		courseRepository.save(course);
	}

	@Test
	public void saveCourseWithStudentAndTeacher() {
		
		Teacher teacher = Teacher.builder().firstName("teacher1").lastName("lastname").build();
		
		Course course = Course.builder()
		.title(".net")
		.credit(5)
		.teacher(teacher)
		.build();

		Guardian guardian = new Guardian("dad", "dad@gmail.com", "999999");

		Student student = Student.builder().emailId("udayyadav@gmail.com").firstName("master").lastName("yadav")
				.guardian(guardian)
				.build();
		
		course.addStudents(student);
		courseRepository.save(course);
	}

}
