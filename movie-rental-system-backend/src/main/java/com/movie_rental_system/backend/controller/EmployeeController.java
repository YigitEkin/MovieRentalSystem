package com.movie_rental_system.backend.controller;

import com.movie_rental_system.backend.entity.Employee;
import com.movie_rental_system.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // get all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    // get specific employee
    @GetMapping("/{employee_name}")
    public ResponseEntity<Employee> getEmployeeByName(@PathVariable String employee_name) {
        return ResponseEntity.ok(employeeService.getEmployeeByName(employee_name));
    }

    // delete employee
    @DeleteMapping("/{employee_name}")
    public ResponseEntity<Employee> deleteEmployeeByName(@PathVariable String employee_name) {
        return ResponseEntity.ok(employeeService.deleteEmployee(employee_name));
    }

    // create employee
    // example request body:
    /* {
            "user_name": "user1",
            "password": "employee password",
            "user_email": "employee email",
            "birth_year": "2001-05-20",
            "salary": 10000
    } */
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }

    // update employee
    // you cant update employee_name
    // example request body: same as create employee
    @PutMapping("/{employee_name}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String employee_name, @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(employee_name, employee));
    }
}
