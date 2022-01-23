package com.example.jpa.repository;

import java.util.List;

import com.example.jpa.entity.Course;
import com.example.jpa.entity.Teacher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeacherRepositoryTest {

	@Autowired
	private TeacherRepository teacherRepository;

	@Test
	public void saveTeacher() {

		Course course = Course.builder().title("DBA").credit(10).build();
		Teacher teacher = Teacher.builder().firstName("Qutub").lastName("Khan").courses(List.of(course)).build();

		teacherRepository.save(teacher);
	}

}
