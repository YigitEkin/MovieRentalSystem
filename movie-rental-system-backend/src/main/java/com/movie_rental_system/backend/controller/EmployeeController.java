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

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{employee_name}")
    public ResponseEntity<Employee> getEmployeeByName(@PathVariable String employee_name) {
        return ResponseEntity.ok(employeeService.getEmployeeByName(employee_name));
    }

    @DeleteMapping("/{employee_name}")
    public ResponseEntity<Employee> deleteEmployeeByName(@PathVariable String employee_name) {
        return ResponseEntity.ok(employeeService.deleteEmployee(employee_name));
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }

    @PutMapping("/{employee_name}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String employee_name, @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(employee_name, employee));
    }
}
