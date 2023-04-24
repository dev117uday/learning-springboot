package com.example.jpa.repository;

import java.util.List;

import com.example.jpa.models.Course;
import com.example.jpa.models.CourseMaterial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {

	@Query
	List<CourseMaterial> findByCourse(Course course);
}
