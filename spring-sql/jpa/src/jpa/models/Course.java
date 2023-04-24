package com.example.jpa.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String courseName;

    @OneToOne(mappedBy = "course", fetch = FetchType.LAZY)
    @ToString.Exclude
    private CourseMaterial courseMaterial;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
    private Teacher teacher;

    @ManyToMany(
        cascade = CascadeType.MERGE
    )
    @JoinTable(name = "student_course_mapping", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "courseId"), inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "studentId"))
    private List<Student> students;

    public void addStudents(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }

}
