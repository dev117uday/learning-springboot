package com.example.jpa.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Builder
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teacherId;
	private String teacherName;

	// @OneToMany(cascade = CascadeType.MERGE)
	// @JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
	// private List<Course> courses;

}
