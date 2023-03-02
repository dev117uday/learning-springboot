package com.fullstack.demo.controller;

import com.fullstack.demo.model.Employee;
import com.fullstack.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("employeeList", employeeService.getAllEmployee());
        return "index";
    }

    @GetMapping("/saveEmpForm")
    public String showNewEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "form/newEmpForm";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        var savedEmp = employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateEmployeeForm(@PathVariable Long id, Model model) {
        var empById = employeeService.getEmployeeById(id);
        model.addAttribute("employee", empById);
        return "form/updateEmpForm";
    }


    @PostMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable("id") Long empId, @ModelAttribute("employee") Employee employee) {
        employeeService.updateEmployee(empId,employee);
        return "redirect:/";
    }

    @GetMapping("/deleteEmpl/{id}")
    public String updateEmployee(@PathVariable("id") Long empId) {
        employeeService.deleteEmployee(empId);
        return "redirect:/";
    }
}
