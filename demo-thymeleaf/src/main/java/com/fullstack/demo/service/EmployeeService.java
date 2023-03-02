package com.fullstack.demo.service;

import com.fullstack.demo.model.Employee;
import com.fullstack.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee emp) {
        return employeeRepository.save(emp);
    }

    public Employee getEmployeeById(long empID) {
        var empFromDB = employeeRepository.findById(empID);
        if (empFromDB.isEmpty()) {
            throw new RuntimeException("Employee with ID " + empID + " not found !");
        }
        return empFromDB.get();

    }

    public Employee updateEmployee(Long empId, Employee employee) {
        employee.setEmpId(empId);
        var empl = getEmployeeById(employee.getEmpId());
        empl.setEmail(employee.getEmail());
        empl.setFirstName(employee.getFirstName());
        empl.setLastName(employee.getLastName());
        employeeRepository.save(empl);
        return empl;
    }

    public void deleteEmployee(Long empId) {
        employeeRepository.deleteById(empId);
    }
}
