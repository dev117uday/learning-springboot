package com.dailycode.tutorial.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.dailycode.tutorial.entity.Department;
import com.dailycode.tutorial.error.DepartmentExceptions;
import com.dailycode.tutorial.service.DepartmentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/depart")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getAllDepartments() {

        return departmentService.fetchAllDepartments();
    }

    @GetMapping("/{departmentId}")
    public Department fetchDepartmentById(@PathVariable("departmentId") Long departmentId) throws DepartmentExceptions {
        return departmentService.fetchDepartmentById(departmentId);
    }

    @PostMapping
    public ResponseEntity<String> saveDepartment(@Valid @RequestBody Department department)
            throws DepartmentExceptions {

        LOGGER.info("Inside saveDepartment of DepartmentController !");
        departmentService.saveDepartment(department);
        return ResponseEntity.status(HttpStatus.OK).body("save successful");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentExceptions {
        departmentService.deleteDepartmentById(departmentId);
        return ResponseEntity.status(HttpStatus.OK).body("Department Deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long departmentId,
            @RequestBody Department department) {
        Optional<Department> updatedDepartment = departmentService.updateDepartment(departmentId, department);
        if (updatedDepartment.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedDepartment.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
