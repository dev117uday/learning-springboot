package com.dailycode.tutorial.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.dailycode.tutorial.entity.Department;
import com.dailycode.tutorial.error.DepartmentExceptions;
import com.dailycode.tutorial.repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void saveDepartment(Department department) throws DepartmentExceptions {

        boolean isPresent = departmentRepository.findById(department.getDepartmentId()).isPresent();
        if (isPresent) {
            throw new DepartmentExceptions(409, "Department with this ID already exists");
        }
        departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentExceptions {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (!department.isPresent()) {
            throw new DepartmentExceptions(404, "Department not found in database");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) throws DepartmentExceptions {
        if (departmentRepository.findById(departmentId).isPresent()) {
            departmentRepository.deleteById(departmentId);
        } else {
            throw new DepartmentExceptions(404,"Department NOT FOUND");
        }
    }

    @Override
    public Optional<Department> updateDepartment(Long departmentId, Department department) {
        Optional<Department> depDB = departmentRepository.findById(departmentId);
        if (!depDB.isPresent()) {
            return null;
        }

        if (!Objects.equals(department.getDepartmentName(), depDB.get().getDepartmentName())) {
            depDB.get().setDepartmentName(department.getDepartmentName());
        }

        if (!Objects.equals(department.getDepartmentAddress(), depDB.get().getDepartmentAddress())) {
            depDB.get().setDepartmentAddress(department.getDepartmentAddress());
        }

        if (!Objects.equals(department.getDepartmentCode(), depDB.get().getDepartmentCode())) {
            depDB.get().setDepartmentCode(department.getDepartmentCode());
        }
        departmentRepository.save(depDB.get());
        return depDB;
    }
}
