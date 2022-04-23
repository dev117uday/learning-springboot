package app.udayyadav.mongo.controller;

import app.udayyadav.mongo.model.Employee;
import app.udayyadav.mongo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeRepository empRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        return empRepository.save(employee);
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployee() {
        return empRepository.findAll();
    }

    @GetMapping("/{empId}")
    public Employee getEmployeeById(@PathVariable String empId) {
        Optional<Employee> opEmployee = empRepository.findById(empId);
        if (opEmployee.isEmpty()) {
            return null;
        }
        return opEmployee.get();
    }

    @GetMapping("/name/{empName}")
    public List<Employee> getEmployeeByName(@PathVariable("empName") String empName) {
        return empRepository.findByName(empName);
    }

    @GetMapping("/namex/{empName}")
    public List<Employee> getEmployeeByNamex(@PathVariable("empName") String empName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(empName)).fields().exclude("age");
        return mongoTemplate.find(query, Employee.class);
    }

    @GetMapping("/name/{empName}/dept/{empDept}")
    public List<Employee> getEmployeeByNameAndDepartment(@PathVariable String empName, @PathVariable String empDept) {
        return empRepository.findByNameAndDepartment(empName, empDept);
    }

    @GetMapping("/age")
    public List<Employee> getEmployeeBtwAge(@RequestParam("minAge") Integer minAge, @RequestParam("maxAge") Integer maxAge) {
        return empRepository.findBetweenAge(minAge, maxAge);
    }

    @GetMapping("/name/o/{empName}")
    public List<Employee> getEmployeeByNameOrderByDepartment(@PathVariable String empName) {
        return empRepository.findByNameOrderByDepartment(empName);
    }

}
