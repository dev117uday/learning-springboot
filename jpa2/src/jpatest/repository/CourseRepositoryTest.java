package com.example.jpa.repository;

import static org.mockito.Mockito.lenient;

import java.util.List;

import com.example.jpa.models.Course;
import com.example.jpa.models.CourseMaterial;
import com.example.jpa.models.Student;
import com.example.jpa.models.Teacher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void AddToDB() {

        var course = Course.builder().courseName("btech").build();
        courseRepository.save(course);

        var courseMaterial = CourseMaterial.builder().materialUrl("www.btech.com").build();
        courseMaterialRepository.save(courseMaterial);

        // var course = Course.builder().courseName("bca").build();
        // var courseMaterial = CourseMaterial.builder().materialUrl("www.bca.com")
        // .course(course).build();
        // courseMaterialRepository.save(courseMaterial);

        // var courseMaterial =
        // CourseMaterial.builder().materialUrl("www.bca.com").build();
        // var savedCourseMaterial = courseMaterialRepository.save(courseMaterial);
        // var course =
        // Course.builder().courseName("bca").courseMaterial(savedCourseMaterial).build();
        // var savedCourse = courseRepository.save(course);
        // System.out.println(savedCourse);

        // var course = Course.builder().courseName("bca").build();
        // var savedCourse = courseRepository.save(course);
        // System.out.println(savedCourse);
        // var courseMaterial =
        // CourseMaterial.builder().materialUrl("www.bca.com").build();

        // courseMaterial.setCourse(savedCourse);
        // var savedCourseMaterial = courseMaterialRepository.save(courseMaterial);
        // System.out.println(savedCourseMaterial.toString());

        // this doesn't not work
        // var courseMaterial =
        // CourseMaterial.builder().materialUrl("www.bca.com").build();
        // var course =
        // Course.builder().courseName("bca").courseMaterial(courseMaterial) .build();
        // var savedCourse = courseRepository.save(course);
        // System.out.println(savedCourse);
    }

    @Test
    public void addCourse() {

        var course = Course.builder().courseName("btech").build();
        courseRepository.save(course);

        var course2 = Course.builder().courseName("bca").build();
        var savedCourse = courseRepository.save(course2);
        System.out.println(savedCourse);

        var courseMaterial = CourseMaterial.builder().materialUrl("http://www.google.com").course(course2).build();
        courseMaterialRepository.save(courseMaterial);

    }

    @Test
    public void addCourseMaterial() {
        var course = Course.builder().courseId(1L).build();
        var courseMaterial = CourseMaterial.builder().materialUrl("http://www.google.com").course(course).build();
        courseMaterialRepository.save(courseMaterial);
    }

    @Test
    public void printAllCourses() {
        var courseMaterialList = courseMaterialRepository.findAll();
        System.out.println(courseMaterialList);

        var course = courseMaterialList.get(0);//
        var foundCourse = courseRepository.findById(course.getCourse().getCourseId());
        System.out.println(foundCourse.get());

    }

    @Test
    public void getCourse() {
        var course = courseRepository.findById(2L);
        System.out.println(course);

        var foundCourse = courseMaterialRepository.findByCourse(course.get());
        System.out.println(foundCourse.toString());
    }

    @Test
    public void saveTeacher() {

        Course course1 = Course.builder().courseName("bba").build();
        Course course2 = Course.builder().courseName("mba").build();

        var savedCourse1 = courseRepository.save(course1);
        var savedCourse2 = courseRepository.save(course2);

        Teacher teacher = Teacher.builder()
                .teacherName("uday yadav")
                // .courses(List.of(savedCourse1, savedCourse2))
                .build();
        teacherRepository.save(teacher);

    }

    @Test
    public void saveTeacherWithCourse() {
        Teacher teacher = Teacher.builder().teacherName("Uday Yadav").build();
        Course course = Course.builder().courseName("ms").teacher(teacher).build();
        courseRepository.save(course);

    }

    @Test
    public void findAllPagination() {
        // PageRequest firstPageWith3 = PageRequest.of(0, 3);
        // var courses = courseRepository.findAll(firstPageWith3).getContent();
        // System.out.println(courses);

        // PageRequest firstPageWith3 = PageRequest.of(1, 3);
        // var courses = courseRepository.findAll(firstPageWith3).getContent();
        // System.out.println(courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {
        Course course = Course.builder().courseName("AI").build();
        Course course2 = Course.builder().courseName("DS").build();

        var s1 = Student.builder().fullName("uday yadav").build();
        var s2 = Student.builder().fullName("uday yadav 2").build();

        studentRepository.save(s1);
        studentRepository.save(s2);

        course.addStudents(s1);
        course.addStudents(s2);

        course2.addStudents(s1);
        course2.addStudents(s2);

        courseRepository.save(course);
        courseRepository.save(course2);
    }

}
