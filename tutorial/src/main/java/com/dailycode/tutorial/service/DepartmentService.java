package com.dailycode.tutorial.service;

import com.dailycode.tutorial.entity.Department;
import com.dailycode.tutorial.error.DepartmentExceptions;

import java.util.*;


public interface DepartmentService {

    void saveDepartment(Department department) throws DepartmentExceptions;

    List<Department> fetchAllDepartments();

    Department fetchDepartmentById(Long departmentId) throws DepartmentExceptions;

    void deleteDepartmentById(Long departmentId) throws DepartmentExceptions;

	Optional<Department> updateDepartment(Long departmentId, Department department);
}
